package com.example.bookly.data.repository

import android.app.Activity
import com.example.bookly.data.service.EmailService
import com.example.bookly.domain.model.User
import com.example.bookly.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.Date
import kotlin.random.Random
import com.google.firebase.firestore.DocumentSnapshot
class AuthRepositoryImpl(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> = runCatching {
        auth.signInWithEmailAndPassword(email.trim(), password).await()
        Unit
    }

    override suspend fun requestRegistrationOtp(
        email: String,
        password: String,
        displayName: String
    ): Result<Unit> = runCatching {
        val formattedEmail = email.trim().lowercase()

        val fetchMethods = auth.fetchSignInMethodsForEmail(formattedEmail).await()
        val isAlreadyRegistered = fetchMethods.signInMethods?.isNotEmpty() == true
        if (isAlreadyRegistered) {
            throw Exception("Địa chỉ Email này đã được đăng ký tài khoản!")
        }

        val otpCode = Random.nextInt(100000, 999999).toString()

        val pendingData = hashMapOf(
            "email" to formattedEmail,
            "password" to password,
            "displayName" to displayName,
            "code" to otpCode,
            "createdAt" to Date()
        )
        firestore.collection("pending_registrations")
            .document(formattedEmail)
            .set(pendingData)
            .await()

        EmailService.sendOtpEmail(formattedEmail, otpCode)

        Unit
    }

    override suspend fun resendOtp(email: String): Result<Unit> = runCatching {
        val formattedEmail = email.trim().lowercase()
        val doc = firestore.collection("pending_registrations").document(formattedEmail).get().await()

        if (!doc.exists()) {
            throw Exception("Thông tin đăng ký không tồn tại. Vui lòng đăng ký lại!")
        }

        val newOtpCode = Random.nextInt(100000, 999999).toString()
        firestore.collection("pending_registrations")
            .document(formattedEmail)
            .update("code", newOtpCode, "createdAt", Date())
            .await()

        EmailService.sendOtpEmail(formattedEmail, newOtpCode)
        Unit
    }

    override suspend fun verifyOtpAndCompleteRegistration(
        email: String,
        inputOtp: String
    ): Result<Unit> = runCatching {
        val formattedEmail = email.trim().lowercase()
        val docRef = firestore.collection("pending_registrations").document(formattedEmail)
        val doc = docRef.get().await()

        if (!doc.exists()) {
            throw Exception("Thông tin đăng ký đã hết hạn. Vui lòng thực hiện lại từ màn hình Đăng ký!")
        }

        val savedCode = doc.getString("code")
        val savedPassword = doc.getString("password")
        val savedDisplayName = doc.getString("displayName") ?: ""

        if (savedCode == null || savedCode != inputOtp.trim()) {
            throw Exception("Mã OTP không chính xác. Vui lòng kiểm tra lại!")
        }

        if (savedPassword == null) {
            throw Exception("Mật khẩu không hợp lệ.")
        }

        val authResult = auth.createUserWithEmailAndPassword(formattedEmail, savedPassword).await()
        val firebaseUser = authResult.user ?: throw Exception("Không thể tạo tài khoản Firebase Auth.")

        val newUser = User(
            uid = firebaseUser.uid,
            email = formattedEmail,
            displayName = savedDisplayName,
            createdAt = Date()
        )
        firestore.collection("users").document(firebaseUser.uid).set(newUser).await()

        docRef.delete().await()

        Unit
    }

    override suspend fun logout() {
        auth.signOut()
    }

    override fun getCurrentUser(): Flow<User?> = callbackFlow {
        var firestoreListener: com.google.firebase.firestore.ListenerRegistration? = null
        val listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val firebaseUser = firebaseAuth.currentUser
            firestoreListener?.remove()
            if (firebaseUser == null) {
                trySend(null)
            } else {
                firestoreListener = firestore.collection("users").document(firebaseUser.uid)
                    .addSnapshotListener { snapshot, error ->
                        if (error != null) {
                            trySend(null)
                            return@addSnapshotListener
                        }
                        val user = snapshot?.toObject(User::class.java) ?: User(
                            uid = firebaseUser.uid,
                            email = firebaseUser.email ?: ""
                        )
                        trySend(user)
                    }
            }
        }
        auth.addAuthStateListener(listener)
        awaitClose {
            auth.removeAuthStateListener(listener)
            firestoreListener?.remove()
        }
    }

    private suspend fun handleSocialUser(firebaseUser: com.google.firebase.auth.FirebaseUser?) {
        val user = firebaseUser ?: throw Exception("Không thể lấy thông tin tài khoản đăng nhập.")
        val userDoc = firestore.collection("users").document(user.uid).get().await()

        val photoUrl = user.photoUrl?.toString()
            ?: user.providerData.firstOrNull { it.photoUrl != null }?.photoUrl?.toString()
        val displayName = user.displayName?.takeIf { it.isNotBlank() } ?: "Độc giả Bookly"
        val email = user.email ?: ""

        if (!userDoc.exists()) {
            val newUser = User(
                uid = user.uid,
                email = email,
                displayName = displayName,
                photoUrl = photoUrl,
                createdAt = Date()
            )
            firestore.collection("users").document(user.uid).set(newUser).await()
        } else {
            val updates = mutableMapOf<String, Any>()
            if (!photoUrl.isNullOrBlank()) {
                updates["photoUrl"] = photoUrl
            }
            if (displayName != "Độc giả Bookly" && userDoc.getString("displayName").isNullOrBlank()) {
                updates["displayName"] = displayName
            }
            if (updates.isNotEmpty()) {
                firestore.collection("users").document(user.uid).update(updates).await()
            }
        }
    }

    override suspend fun signInWithGoogle(idToken: String): Result<Unit> = runCatching {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        val authResult = auth.signInWithCredential(credential).await()
        handleSocialUser(authResult.user)
    }

    override suspend fun signInWithGoogleWeb(activity: Activity): Result<Unit> = runCatching {
        val provider = OAuthProvider.newBuilder("google.com").apply {
            addCustomParameter("prompt", "select_account")
        }.build()
        val authResult = auth.startActivityForSignInWithProvider(activity, provider).await()
        handleSocialUser(authResult.user)
    }

    override suspend fun signInWithGitHub(activity: Activity): Result<Unit> = runCatching {
        val provider = OAuthProvider.newBuilder("github.com").apply {
            scopes = listOf("user:email", "read:user")
        }.build()
        val authResult = auth.startActivityForSignInWithProvider(activity, provider).await()
        handleSocialUser(authResult.user)
    }

    override suspend fun signInWithFacebook(activity: Activity): Result<Unit> = runCatching {
        val provider = OAuthProvider.newBuilder("facebook.com").apply {
            addCustomParameter("display", "touch")
            scopes = listOf("email", "public_profile")
        }.build()
        val authResult = auth.startActivityForSignInWithProvider(activity, provider).await()
        handleSocialUser(authResult.user)
    }
}
