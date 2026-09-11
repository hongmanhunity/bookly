package com.example.bookly.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookly.data.local.dao.BookDao
import com.example.bookly.data.local.entity.BookmarkEntity
import com.example.bookly.data.local.entity.ReadingProgressEntity

@Database(
    entities = [
        BookmarkEntity::class,
        ReadingProgressEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class BooklyDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}