package com.example.bookly.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.data.repository.AuthRepositoryImpl
import com.example.bookly.domain.model.User
import com.example.bookly.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository = AuthRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = AuthUiState.Error("Vui lòng điền đầy đủ Email và Mật khẩu")
            return
        }

        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            val result = authRepository.login(email.trim(), password)

            result.onSuccess { user ->
                _uiState.value = AuthUiState.Success(user)
            }.onFailure { exception ->
                _uiState.value = AuthUiState.Error(
                    exception.localizedMessage ?: "Đăng nhập thất bại. Vui lòng kiểm tra lại thông tin."
                )
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
            val result = authRepository.register(email.trim(), password, displayName.trim())

            result.onSuccess { user ->
                _uiState.value = AuthUiState.Success(user)
            }.onFailure { exception ->
                _uiState.value = AuthUiState.Error(
                    exception.localizedMessage ?: "Đăng ký thất bại. Vui lòng thử lại."
                )
            }
        }
    }

    fun resetUiState() {
        _uiState.value = AuthUiState.Idle
    }
}
