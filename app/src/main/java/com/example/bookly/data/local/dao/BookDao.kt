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

    // Lấy toàn bộ tiến trình đọc để phân tích thống kê
    @Query("SELECT * FROM reading_progress")
    fun getAllReadingProgress(): Flow<List<ReadingProgressEntity>>
    // Đếm số lượng sách đang đọc dở (chưa đọc xong, dành cho màn hình Profile)
    @Query("SELECT COUNT(*) FROM reading_progress WHERE isFinished = 0")
    fun getCurrentlyReadingCount(): Flow<Int>

    // Đếm số lượng sách đã đọc xong (dành cho màn hình Profile)
    @Query("SELECT COUNT(*) FROM reading_progress WHERE isFinished = 1")
    fun getFinishedReadingCount(): Flow<Int>

    // Xóa toàn bộ bookmark khi nạp lại dữ liệu
    @Query("DELETE FROM bookmarks")
    suspend fun clearAllBookmarks(): Int

    // Xóa toàn bộ tiến trình đọc khi nạp lại dữ liệu
    @Query("DELETE FROM reading_progress")
    suspend fun clearAllReadingProgress(): Int
}