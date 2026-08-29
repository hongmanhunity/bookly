package com.example.bookly.ui.book

import com.example.bookly.domain.model.Book

sealed class DetailBookUiState {
    data object Loading: DetailBookUiState()
    data class Success(val books: Book): DetailBookUiState()
    data class Error(val message: String): DetailBookUiState()
}