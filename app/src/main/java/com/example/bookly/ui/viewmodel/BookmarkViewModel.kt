package com.example.bookly.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.data.local.dao.BookDao
import com.example.bookly.data.local.entity.BookmarkEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class BookmarkViewModel(
    private val bookDao: BookDao
): ViewModel() {
    val bookmarks: StateFlow<List<BookmarkEntity>> = bookDao.getAllBookmarks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}