package com.example.bookly.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey
    val bookId: String,
    val title: String,
    val author: String,
    val coverUrl: String,
    val category: String,
    val rating: Double,
    val savedAt: Long = System.currentTimeMillis()
)
