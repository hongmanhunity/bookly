package com.example.bookly.ui.state

sealed class AuthUiState {
    data object Idle : AuthUiState()                             // Trạng thái chờ ban đầu
    data object Loading : AuthUiState()                          // Đang xử lý Login/Register với Firebase
    data object Success : AuthUiState()                          // Đăng nhập thành công
    data class OtpSent(val otpCode: String) : AuthUiState()      // Mã OTP đã phát gửi tới Email
    data object OtpVerified : AuthUiState()                      // Mã OTP nhập chính xác
    data class Error(val message: String) : AuthUiState()        // Xảy ra lỗi (Sai pass, trùng email...)
}
