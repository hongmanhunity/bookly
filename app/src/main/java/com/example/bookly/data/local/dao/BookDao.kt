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
    // Thêm sách vào Bookmark (nếu đã có thì ghi đè)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: BookmarkEntity): Long
    // Xóa sách khỏi Bookmark
    @Query("DELETE FROM bookmarks WHERE bookId = :bookId")
    suspend fun removeBookmark(bookId: String): Int
    // Đếm tổng số sách đã bookmark (dành cho màn hình Profile)
    @Query("SELECT COUNT(*) FROM bookmarks")
    fun getBookmarkCount(): Flow<Int>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveReadingProgress(progress: ReadingProgressEntity): Long
    // Lấy tiến trình đọc của 1 cuốn sách
    @Query("SELECT * FROM reading_progress WHERE bookId = :bookId")
    fun getReadingProgress(bookId: String): Flow<ReadingProgressEntity?>
    // Đếm số lượng sách đang đọc dở (dành cho màn hình Profile)
    @Query("SELECT COUNT(*) FROM reading_progress")
    fun getCurrentlyReadingCount(): Flow<Int>
}