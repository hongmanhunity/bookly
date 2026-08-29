package com.example.bookly.ui.auth

import com.example.bookly.domain.model.User

sealed class AuthUiState {
    data object Idle : AuthUiState()                             // Trạng thái chờ ban đầu
    data object Loading : AuthUiState()                          // Đang xử lý Login/Register với Firebase
    data class Success(val user: User) : AuthUiState()           // Đăng nhập/Đăng ký thành công
    data class Error(val message: String) : AuthUiState()        // Xảy ra lỗi (Sai pass, trùng email...)
}
