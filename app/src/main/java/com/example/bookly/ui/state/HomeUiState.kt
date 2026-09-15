package com.example.bookly.ui.state

import com.example.bookly.domain.model.Book

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Error(val message: String) : HomeUiState
    data class Success(
        val featuredBooks: List<Book>,
        val trendingBooks: List<Book>,
        val newReleases: List<Book> = emptyList(),
        val userDisplayName: String = "Độc Giả Bookly"
    ) : HomeUiState
}
