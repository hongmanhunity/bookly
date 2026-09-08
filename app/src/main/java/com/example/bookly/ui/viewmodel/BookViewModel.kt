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
    //Category
    private val _selectedCategory  = MutableStateFlow("Tất cả")
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
            private fun applyFilter() {
                val query = _searchQuery.value
                val filterBooks = if(query.isBlank()) {
                    allBooks
                } else {
                    allBooks.filter { book ->
                        book.title.contains(query, ignoreCase = true) ||
                                book.author.contains(query, ignoreCase = true)
                    }
                }
                _uiState.value = BookUiState.Success(filterBooks)
            }
        }

