package com.example.bookly.domain.repository

import android.app.Activity
import com.example.bookly.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>

    suspend fun requestRegistrationOtp(email: String, password: String, displayName: String): Result<Unit>

    suspend fun resendOtp(email: String): Result<Unit>

    suspend fun verifyOtpAndCompleteRegistration(email: String, inputOtp: String): Result<Unit>

    suspend fun logout()

    fun getCurrentUser(): Flow<User?>

    suspend fun signInWithGoogle(idToken: String): Result<Unit>

    suspend fun signInWithGoogleWeb(activity: Activity): Result<Unit>

    suspend fun signInWithGitHub(activity: Activity): Result<Unit>

    suspend fun signInWithFacebook(activity: Activity): Result<Unit>
}
