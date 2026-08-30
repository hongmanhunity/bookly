package com.example.bookly.ui.state

import com.example.bookly.domain.model.Book
import com.example.bookly.domain.model.Chapter

sealed class DetailBookUiState {
    data object Loading : DetailBookUiState()
    data class Success(
        val book: Book,
        val chapters: List<Chapter> = emptyList()
    ) : DetailBookUiState() {
        // Alias cho thuộc tính cũ để tránh lỗi tương thích
        val books: Book get() = book
    }
    data class Error(val message: String) : DetailBookUiState()
}
