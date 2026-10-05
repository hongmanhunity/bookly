package com.example.bookly.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.data.local.dao.BookDao
import com.example.bookly.data.local.entity.BookmarkEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class CategoryStat(
    val category: String,
    val count: Int,
    val percentage: Float
)

data class BookmarkStats(
    val totalBooks: Int = 0,
    val readingCount: Int = 0,
    val finishedCount: Int = 0,
    val unreadCount: Int = 0,
    val completionRate: Float = 0f,
    val averageRating: Double = 0.0,
    val totalCategories: Int = 0,
    val topCategory: String = "",
    val categoryDistribution: List<CategoryStat> = emptyList()
)

class BookmarkViewModel(
    private val bookDao: BookDao
): ViewModel() {

    val bookmarks: StateFlow<List<BookmarkEntity>> = bookDao.getAllBookmarks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()

    fun onCategorySelect(category: String?) {
        _selectedCategory.value = if (_selectedCategory.value == category) null else category
    }

    val filteredBookmarks: StateFlow<List<BookmarkEntity>> = combine(
        bookmarks,
        _selectedCategory
    ) { list, category ->
        if (category == null || category == "Tất cả") {
            list
        } else {
            list.filter { it.category.equals(category, ignoreCase = true) }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val stats: StateFlow<BookmarkStats> = combine(
        bookmarks,
        bookDao.getAllReadingProgress()
    ) { bookmarkList, progressList ->
        if (bookmarkList.isEmpty()) {
            BookmarkStats()
        } else {
            val total = bookmarkList.size
            val avgRating = bookmarkList.map { it.rating }.filter { it > 0 }.let {
                if (it.isNotEmpty()) it.average() else 0.0
            }

            val categoryGroups = bookmarkList.groupBy { it.category.ifBlank { "Chưa phân loại" } }
            val catStats = categoryGroups.map { (cat, booksInCat) ->
                CategoryStat(
                    category = cat,
                    count = booksInCat.size,
                    percentage = booksInCat.size.toFloat() / total
                )
            }.sortedByDescending { it.count }

            val topCat = catStats.firstOrNull()?.category ?: ""

            val progressMap = progressList.associateBy { it.bookId }
            var reading = 0
            var finished = 0
            var unread = 0

            for (book in bookmarkList) {
                val prog = progressMap[book.bookId]
                when {
                    prog == null -> unread++
                    prog.isFinished -> finished++
                    else -> reading++
                }
            }

            val completionRate = if (total > 0) finished.toFloat() / total else 0f

            BookmarkStats(
                totalBooks = total,
                readingCount = reading,
                finishedCount = finished,
                unreadCount = unread,
                completionRate = completionRate,
                averageRating = avgRating,
                totalCategories = categoryGroups.size,
                topCategory = topCat,
                categoryDistribution = catStats
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = BookmarkStats()
    )
}
