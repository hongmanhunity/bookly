package com.example.bookly.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.data.repository.BookRepositoryImpl
import com.example.bookly.domain.repository.BookRepository
import com.example.bookly.ui.state.BookUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BookViewModel(
    private val repository: BookRepository = BookRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow<BookUiState>(BookUiState.Loading)
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()

    init {
        loadBooks()
    }

    fun loadBooks() {
        viewModelScope.launch {
            _uiState.value = BookUiState.Loading
            repository.getBooks()
                .catch { err ->
                    _uiState.value = BookUiState.Error(err.message ?: "Lỗi tải sách từ Firestore")
                }
                .collect { books ->
                    _uiState.value = BookUiState.Success(books)
                }
        }
    }
}
