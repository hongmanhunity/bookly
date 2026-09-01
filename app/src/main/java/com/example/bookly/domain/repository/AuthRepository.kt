package com.example.bookly.domain.repository

import com.example.bookly.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    // Đăng nhập bằng Email & Password
    suspend fun login(email: String, password: String): Result<Unit>
    
    // Khởi tạo quy trình Đăng ký: Kiểm tra Email + Sinh OTP 6 số + Gửi Email (CHƯA tạo Auth User)
    suspend fun requestRegistrationOtp(email: String, password: String, displayName: String): Result<String>
    
    // Gửi lại mã OTP mới cho thông tin đăng ký đang chờ
    suspend fun resendOtp(email: String): Result<String>
    
    // Đối soát OTP: Sau khi đúng 6 số mới CHÍNH THỨC tạo tài khoản Firebase Auth
    suspend fun verifyOtpAndCompleteRegistration(email: String, inputOtp: String): Result<Unit>
    
    // Đăng xuất
    suspend fun logout()
    
    // Lắng nghe trạng thái User hiện tại (Realtime Flow)
    fun getCurrentUser(): Flow<User?>
}
