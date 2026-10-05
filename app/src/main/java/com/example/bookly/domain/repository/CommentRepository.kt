package com.example.bookly.domain.repository

import com.example.bookly.domain.model.Comment
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    fun getComments(bookId: String, chapterId: String): Flow<List<Comment>>
    suspend fun sendComment(bookId: String, chapterId: String, comment: String): Result<Unit>
    suspend fun deleteComment(bookId: String, chapterId: String, commentId: String): Result<Unit>
}
