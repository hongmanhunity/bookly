package com.example.bookly.data.repository

import android.util.Log
import com.example.bookly.domain.model.User
import com.example.bookly.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.Date

class AuthRepositoryImpl(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> = runCatching {
        auth.signInWithEmailAndPassword(email, password).await()
        Unit
    }

    override suspend fun register(
        email: String,
        password: String,
        displayName: String
    ): Result<Unit> = runCatching {
        val authResult = auth.createUserWithEmailAndPassword(email, password).await()
        val firebaseUser = authResult.user ?: throw Exception("Đăng ký thất bại")
        val newUser = User(
            uid = firebaseUser.uid,
            email = email,
            displayName = displayName,
            createdAt = Date()
        )
        firestore.collection("users").document(firebaseUser.uid).set(newUser).await()
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

