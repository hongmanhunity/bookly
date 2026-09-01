package com.example.bookly.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.domain.model.User
import com.example.bookly.domain.repository.AuthRepository
import com.example.bookly.data.repository.AuthRepositoryImpl
import com.example.bookly.ui.state.AuthUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository = AuthRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    init {
        observeCurrentUser()
    }

    private fun observeCurrentUser() {
        viewModelScope.launch {
            authRepository.getCurrentUser().collect { user ->
                _currentUser.value = user
            }
        }
    }

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = AuthUiState.Error("Vui lòng điền đầy đủ Email và Mật khẩu")
            return
        }

        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            val result = authRepository.login(email.trim(), password)

            result.onSuccess {
                _uiState.value = AuthUiState.Success
            }.onFailure {
                _uiState.value = AuthUiState.Error("Tài khoản hoặc mật khẩu không chính xác")
            }
        }
    }

    fun register(email: String, password: String, displayName: String) {
        if (email.isBlank() || password.isBlank() || displayName.isBlank()) {
            _uiState.value = AuthUiState.Error("Vui lòng điền đầy đủ thông tin")
            return
        }

        if (password.length < 6) {
            _uiState.value = AuthUiState.Error("Mật khẩu phải có ít nhất 6 ký tự")
            return
        }

        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            // Yêu cầu phát OTP & tạm lưu dữ liệu (CHƯA tạo Firebase Auth User)
            val otpResult = authRepository.requestRegistrationOtp(email.trim(), password, displayName.trim())

            otpResult.onSuccess { otpCode ->
                _uiState.value = AuthUiState.OtpSent(otpCode)
            }.onFailure { error ->
                _uiState.value = AuthUiState.Error(error.message ?: "Đăng ký thất bại. Vui lòng thử lại!")
            }
        }
    }

    fun verifyOtp(email: String, inputOtp: String) {
        if (inputOtp.length < 6) {
            _uiState.value = AuthUiState.Error("Vui lòng nhập đủ 6 chữ số OTP")
            return
        }

        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            // Xác thực OTP -> Nếu đúng mới CHÍNH THỨC tạo tài khoản Firebase Auth
            val result = authRepository.verifyOtpAndCompleteRegistration(email.trim(), inputOtp)

            result.onSuccess {
                _uiState.value = AuthUiState.OtpVerified
            }.onFailure { error ->
                _uiState.value = AuthUiState.Error(error.message ?: "Mã OTP không chính xác. Vui lòng thử lại!")
            }
        }
    }

    fun resendOtp(email: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            val otpResult = authRepository.resendOtp(email.trim())
            otpResult.onSuccess { otpCode ->
                _uiState.value = AuthUiState.OtpSent(otpCode)
            }.onFailure { error ->
                _uiState.value = AuthUiState.Error(error.message ?: "Không thể gửi lại mã OTP. Vui lòng thử lại sau.")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
            _uiState.value = AuthUiState.Idle
        }
    }

    fun resetUiState() {
        _uiState.value = AuthUiState.Idle
    }
}
