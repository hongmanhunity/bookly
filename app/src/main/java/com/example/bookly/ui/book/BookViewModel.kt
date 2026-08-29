package com.example.bookly.ui.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.data.repository.BookRepositoryImpl
import com.example.bookly.domain.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BookViewModel (
    private val repository: BookRepository = BookRepositoryImpl() // 👈 Thêm giá trị mặc định
): ViewModel() {
    private val _uiState = MutableStateFlow<BookUiState>(BookUiState.Loading)
    val uiState: StateFlow<BookUiState> = _uiState

    // 👈 1. Thêm init để tự động gọi loadBook()
    init {
        loadBook()
    }

    private fun loadBook() {
        // 1. Chạy Seeder ở Coroutine riêng
        // 2. Đọc dữ liệu sách real-time
        viewModelScope.launch {
            repository.getBooks()
                .catch { err ->
                    _uiState.value = BookUiState.Error(err.message ?: "Lỗi tải sách từ Firestore")
                }
                .collect { booksList ->
                    _uiState.value = BookUiState.Success(booksList)
                }
        }
    }
}
