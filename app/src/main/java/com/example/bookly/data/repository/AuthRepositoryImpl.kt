package com.example.bookly.data.repository

import com.example.bookly.data.service.EmailService
import com.example.bookly.domain.model.User
import com.example.bookly.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.Date
import kotlin.random.Random

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
    ): Result<String> = runCatching {
        val formattedEmail = email.trim().lowercase()

        // 1. Kiểm tra xem Email đã đăng ký trong Firebase Auth chưa
        val fetchMethods = auth.fetchSignInMethodsForEmail(formattedEmail).await()
        val isAlreadyRegistered = fetchMethods.signInMethods?.isNotEmpty() == true
        if (isAlreadyRegistered) {
            throw Exception("Địa chỉ Email này đã được đăng ký tài khoản!")
        }

        // 2. Sinh mã OTP 6 chữ số ngẫu nhiên
        val otpCode = Random.nextInt(100000, 999999).toString()

        // 3. Tạm lưu thông tin đăng ký vào collection pending_registrations (CHƯA tạo Firebase Auth User)
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

        // 4. Phát thư Email HTML tới hòm thư Gmail người dùng
        EmailService.sendOtpEmail(formattedEmail, otpCode)

        otpCode
    }

    override suspend fun resendOtp(email: String): Result<String> = runCatching {
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
        newOtpCode
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

        // 🎯 ĐÃ XÁC THỰC OTP THÀNH CÔNG -> BÂY GIỜ MỚI CHÍNH THỨC TẠO TÀI KHOẢN FIREBASE AUTH
        val authResult = auth.createUserWithEmailAndPassword(formattedEmail, savedPassword).await()
        val firebaseUser = authResult.user ?: throw Exception("Không thể tạo tài khoản Firebase Auth.")

        // Tạo hồ sơ người dùng trong Firestore
        val newUser = User(
            uid = firebaseUser.uid,
            email = formattedEmail,
            displayName = savedDisplayName,
            createdAt = Date()
        )
        firestore.collection("users").document(firebaseUser.uid).set(newUser).await()

        // Đã hoàn tất -> Xóa tài liệu tạm pending_registrations
        docRef.delete().await()

        Unit
    }

    override suspend fun logout() {
        auth.signOut()
    }

    override fun getCurrentUser(): Flow<User?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser == null) {
                trySend(null)
            } else {
                firestore.collection("users").document(firebaseUser.uid)
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
        awaitClose { auth.removeAuthStateListener(listener) }
    }
}
