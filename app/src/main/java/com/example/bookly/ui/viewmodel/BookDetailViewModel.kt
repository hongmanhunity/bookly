package com.example.bookly.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.data.repository.BookRepositoryImpl
import com.example.bookly.domain.repository.BookRepository
import com.example.bookly.ui.state.DetailBookUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val repository: BookRepository = BookRepositoryImpl()
) : ViewModel() {
    private val _uiState = MutableStateFlow<DetailBookUiState>(DetailBookUiState.Loading)
    val uiState: StateFlow<DetailBookUiState> = _uiState

    fun loadBookDetail(bookId: String) {
        val current = _uiState.value
        if (current is DetailBookUiState.Success && current.book.id == bookId) {
            return
        }
        viewModelScope.launch {
            _uiState.value = DetailBookUiState.Loading
            combine(
                repository.getBookById(bookId),
                repository.getChapters(bookId)
            ) { book, chapters ->
                DetailBookUiState.Success(book = book, chapters = chapters)
            }
            .catch { err ->
                _uiState.value = DetailBookUiState.Error(err.message ?: "Lỗi tải chi tiết sách")
            }
            .collect { state ->
                _uiState.value = state
            }
        }
    }
}
