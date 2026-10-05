package com.example.bookly.ui.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.domain.model.User
import com.example.bookly.domain.repository.AuthRepository
import com.example.bookly.data.repository.AuthRepositoryImpl
import com.example.bookly.ui.state.AuthUiState
import com.example.bookly.utils.AuthErrorParser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
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
            }.onFailure { error ->
                val friendlyMessage = AuthErrorParser.parse(error) ?: "Đăng nhập thất bại. Vui lòng kiểm tra lại thông tin."
                _uiState.value = AuthUiState.Error(friendlyMessage)
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
            val otpResult = authRepository.requestRegistrationOtp(email.trim(), password, displayName.trim())

            otpResult.onSuccess {
                _uiState.value = AuthUiState.OtpSent
            }.onFailure { error ->
                val friendlyMessage = AuthErrorParser.parse(error) ?: "Đăng ký thất bại. Vui lòng thử lại!"
                _uiState.value = AuthUiState.Error(friendlyMessage)
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
            val result = authRepository.verifyOtpAndCompleteRegistration(email.trim(), inputOtp)

            result.onSuccess {
                _uiState.value = AuthUiState.OtpVerified
            }.onFailure { error ->
                val friendlyMessage = AuthErrorParser.parse(error) ?: "Mã OTP không chính xác. Vui lòng thử lại!"
                _uiState.value = AuthUiState.Error(friendlyMessage)
            }
        }
    }

    fun resendOtp(email: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            val otpResult = authRepository.resendOtp(email.trim())
            otpResult.onSuccess {
                _uiState.value = AuthUiState.OtpSent
            }.onFailure { error ->
                val friendlyMessage = AuthErrorParser.parse(error) ?: "Không thể gửi lại mã OTP. Vui lòng thử lại sau."
                _uiState.value = AuthUiState.Error(friendlyMessage)
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

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            val result = authRepository.signInWithGoogle(idToken)
            result.onSuccess {
                _uiState.value = AuthUiState.Success
            }.onFailure { error ->
                val friendlyMessage = AuthErrorParser.parse(error)
                if (friendlyMessage != null) {
                    _uiState.value = AuthUiState.Error(friendlyMessage)
                } else {
                    _uiState.value = AuthUiState.Idle
                }
            }
        }
    }

    fun signInWithGitHub(activity: Activity) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            val result = authRepository.signInWithGitHub(activity)
            result.onSuccess {
                _uiState.value = AuthUiState.Success
            }.onFailure { error ->
                val friendlyMessage = AuthErrorParser.parse(error)
                if (friendlyMessage != null) {
                    _uiState.value = AuthUiState.Error(friendlyMessage)
                } else {
                    _uiState.value = AuthUiState.Idle
                }
            }
        }
    }

    fun signInWithFacebook(activity: Activity) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            val result = authRepository.signInWithFacebook(activity)
            result.onSuccess {
                _uiState.value = AuthUiState.Success
            }.onFailure { error ->
                val friendlyMessage = AuthErrorParser.parse(error)
                if (friendlyMessage != null) {
                    _uiState.value = AuthUiState.Error(friendlyMessage)
                } else {
                    _uiState.value = AuthUiState.Idle
                }
            }
        }
    }
}
