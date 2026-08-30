package com.example.bookly.domain.repository

import com.example.bookly.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    // Đăng nhập bằng Email & Password
    suspend fun login(email: String, password: String): Result<Unit>
    // Đăng ký tài khoản mới với Email, Password & Tên hiển thị
    suspend fun register(email: String, password: String, displayName: String): Result<Unit>
    // Đăng xuất
    suspend fun logout()
    // Lắng nghe trạng thái User hiện tại (Realtime Flow)
    fun getCurrentUser(): Flow<User?>
}