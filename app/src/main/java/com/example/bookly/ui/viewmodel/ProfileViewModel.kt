package com.example.bookly.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.data.local.ThemeManager
import com.example.bookly.data.local.dao.BookDao
import com.example.bookly.domain.repository.AuthRepository
import com.example.bookly.ui.state.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authRepository: AuthRepository,
    private val bookDao: BookDao,
    private val themeManager: ThemeManager
) : ViewModel() {

    val isDarkMode: StateFlow<Boolean> = themeManager.isDarkMode

    fun toggleDarkMode(enabled: Boolean) {
        themeManager.setDarkMode(enabled)
    }

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadUserProfile()
    }
    val bookmarkCount: StateFlow<Int> = bookDao.getBookmarkCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    val currentlyReadingCount: StateFlow<Int> = bookDao.getCurrentlyReadingCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    val finishedReadingCount: StateFlow<Int> = bookDao.getFinishedReadingCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )
    fun loadUserProfile() {
        viewModelScope.launch {
            authRepository.getCurrentUser().collect { user ->
                _uiState.value = if (user != null) {
                    ProfileUiState.Success(user)
                } else {
                    ProfileUiState.Error("Không tìm thấy thông tin người dùng")
                }
            }
        }
    }

    fun logout(onLogoutSuccess: () -> Unit) {
        viewModelScope.launch {
            authRepository.logout()
            onLogoutSuccess()
        }
    }
}
