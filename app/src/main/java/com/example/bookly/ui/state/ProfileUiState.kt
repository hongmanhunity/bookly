package com.example.bookly.ui.state

import com.example.bookly.domain.model.User

sealed class ProfileUiState {
    data object Loading : ProfileUiState()
    data class Success(val user: User) : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
}
