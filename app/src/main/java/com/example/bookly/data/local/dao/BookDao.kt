package com.example.bookly.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookly.data.local.entity.BookmarkEntity
import com.example.bookly.data.local.entity.ReadingProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
@JvmSuppressWildcards
interface BookDao {
    @Query("SELECT * FROM bookmarks ORDER BY savedAt DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>
    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE bookId = :bookId)")
    fun isBookmarked(bookId: String): Flow<Boolean>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: BookmarkEntity): Long
    @Query("DELETE FROM bookmarks WHERE bookId = :bookId")
    suspend fun removeBookmark(bookId: String): Int
    @Query("SELECT COUNT(*) FROM bookmarks")
    fun getBookmarkCount(): Flow<Int>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveReadingProgress(progress: ReadingProgressEntity): Long
    @Query("SELECT * FROM reading_progress WHERE bookId = :bookId")
    fun getReadingProgress(bookId: String): Flow<ReadingProgressEntity?>

    @Query("SELECT * FROM reading_progress")
    fun getAllReadingProgress(): Flow<List<ReadingProgressEntity>>
    @Query("SELECT COUNT(*) FROM reading_progress WHERE isFinished = 0")
    fun getCurrentlyReadingCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM reading_progress WHERE isFinished = 1")
    fun getFinishedReadingCount(): Flow<Int>

    @Query("DELETE FROM bookmarks")
    suspend fun clearAllBookmarks(): Int

    @Query("DELETE FROM reading_progress")
    suspend fun clearAllReadingProgress(): Int
}
