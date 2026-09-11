package com.example.bookly.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.data.repository.BookRepositoryImpl
import com.example.bookly.domain.model.Book
import com.example.bookly.domain.repository.BookRepository
import com.example.bookly.ui.state.BookUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BookViewModel(
    private val repository: BookRepository
) : ViewModel() {
    private var allBooks = emptyList<Book>()
    //Filter
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    // Category
    val categories = listOf("Tất cả", "Isekai", "Hành Động", "Kỳ Ảo", "Học Đường", "Phiêu Lưu", "Khoa Học Viễn Tưởng", "Lãng Mạn")
    private val _selectedCategory = MutableStateFlow("Tất cả")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

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
                    allBooks = books
                    applyFilter()
                }
        }
    }

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
        applyFilter()
    }

    fun clearQuery() {
        _searchQuery.value = ""
        applyFilter()
    }

    fun onCategorySelect(category: String) {
        _selectedCategory.value = category
        applyFilter()
    }

    private fun applyFilter() {
        val query = _searchQuery.value.trim()
        val category = _selectedCategory.value

        val filteredBooks = allBooks.filter { book ->
            val matchesQuery = query.isBlank() ||
                book.title.contains(query, ignoreCase = true) ||
                book.author.contains(query, ignoreCase = true)
            val matchesCategory = category == "Tất cả" ||
                book.category.contains(category, ignoreCase = true)
            matchesQuery && matchesCategory
        }
        _uiState.value = BookUiState.Success(filteredBooks)
    }
}

