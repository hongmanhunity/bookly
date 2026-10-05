package com.example.bookly.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.data.local.dao.BookDao
import com.example.bookly.data.local.entity.BookmarkEntity
import com.example.bookly.data.local.entity.ReadingProgressEntity
import com.example.bookly.domain.model.Book
import com.example.bookly.domain.repository.BookRepository
import com.example.bookly.ui.state.DetailBookUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val repository: BookRepository,
    private val bookDao: BookDao
) : ViewModel() {
    private val _uiState = MutableStateFlow<DetailBookUiState>(DetailBookUiState.Loading)
    val uiState: StateFlow<DetailBookUiState> = _uiState

    fun loadBookDetail(bookId: String) {
        _uiState.value = DetailBookUiState.Loading
        viewModelScope.launch {
            combine(
                repository.getBookById(bookId),
                repository.getChapters(bookId)
            ) { book, chapters ->
                DetailBookUiState.Success(book = book, chapters = chapters)
            }.catch { err ->
                _uiState.value = DetailBookUiState.Error(err.message ?: "Lỗi tải chi tiết sách")
            }.collect { successState ->
                _uiState.value = successState
            }
        }
    }
    fun isBookmarked(bookId: String): Flow<Boolean> = bookDao.isBookmarked(bookId)

    fun getReadingProgress(bookId: String): Flow<ReadingProgressEntity?> =
        bookDao.getReadingProgress(bookId)

    fun saveReadingProgress(
        bookId: String,
        chapterNumber: Int,
        chapterTitle: String,
        totalChapters: Int,
        isFinished: Boolean = false
    ) {
        viewModelScope.launch {
            bookDao.saveReadingProgress(
                ReadingProgressEntity(
                    bookId = bookId,
                    lastChapterNumber = chapterNumber,
                    lastChapterTitle = chapterTitle,
                    totalChapters = totalChapters,
                    isFinished = isFinished,
                    lastReadAt = System.currentTimeMillis()
                )
            )
        }
    }

    fun toggleBookmark(book: Book, isCurrentlyBookmarked: Boolean) {
        viewModelScope.launch {
            if(isCurrentlyBookmarked) {
                bookDao.removeBookmark(book.id)
            }
            else {
                bookDao.addBookmark(
                    BookmarkEntity(
                        bookId = book.id,
                        title = book.title,
                        author = book.author,
                        coverUrl = book.coverUrl,
                        category = book.category,
                        rating = book.rating
                    )
                )
            }
        }
    }
}
