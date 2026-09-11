package com.example.bookly.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reading_progress")
data class ReadingProgressEntity(
    @PrimaryKey
    val bookId: String,
    val lastChapterNumber: Int,
    val lastChapterTitle: String = "",
    val totalChapters: Int = 0,
    val isFinished: Boolean = false,
    val lastReadAt: Long = System.currentTimeMillis()
)
