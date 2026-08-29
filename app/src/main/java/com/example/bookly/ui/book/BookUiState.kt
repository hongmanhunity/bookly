package com.example.bookly.ui.book

import com.example.bookly.domain.model.Book

sealed class BookUiState {
    data object Loading: BookUiState()
    data class Success(val books: List<Book>): BookUiState()
    data class Error(val message: String): BookUiState()
}