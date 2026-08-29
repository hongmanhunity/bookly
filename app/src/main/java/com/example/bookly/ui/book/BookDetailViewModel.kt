package com.example.bookly.ui.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.data.repository.BookRepositoryImpl
import com.example.bookly.domain.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val repository: BookRepository = BookRepositoryImpl()
): ViewModel() {
    private val _uiState = MutableStateFlow<DetailBookUiState>(DetailBookUiState.Loading)
    val uiState: StateFlow<DetailBookUiState> = _uiState

    fun loadBookDetail(bookId: String) {
        viewModelScope.launch {
            repository.getBookById(bookId)
                .catch { err -> _uiState.value = DetailBookUiState.Error(err.message ?: "Lỗi tải chi tiết") }
                .collect { book -> _uiState.value = DetailBookUiState.Success(book) }
        }
    }
}