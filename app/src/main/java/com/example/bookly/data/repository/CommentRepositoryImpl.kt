package com.example.bookly.data.repository

import com.example.bookly.domain.model.Comment
import com.example.bookly.domain.repository.CommentRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class CommentRepositoryImpl (
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    ): CommentRepository {
    override fun getComments(bookId: String, chapterId: String): Flow<List<Comment>> = callbackFlow {
        if(bookId.isBlank() || chapterId.isBlank()) {
            trySend(emptyList())
            close()
            return@callbackFlow
        }
        val listener = firestore.collection("books")
            .document(bookId)
            .collection("chapters")
            .document(chapterId)
            .collection("comments")
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshots, exception ->
                if(exception != null) {
                    close(exception)
                    return@addSnapshotListener
                }
                val comments = snapshots?.documents?.map { document ->
                    Comment(
                        id = document.id,
                        userId = document.getString("userId") ?: "",
                        userName = document.getString("userName") ?: "Độc Giả Bookly",
                        userAvatar = document.getString("userAvatar"),
                        content = document.getString("content") ?: "",
                        createdAt = document.getLong("createdAt") ?: System.currentTimeMillis()
                    )
                } ?: emptyList()
                trySend(comments)
            }
            awaitClose { listener.remove() }
    }

    override suspend fun sendComment(
        bookId: String,
        chapterId: String,
        comment: String
    ): Result<Unit> = runCatching {
        val currentUser = auth.currentUser ?: throw Exception("Vui lòng đăng nhập để bình luận!")
        if(comment.trim().isEmpty()) {
            throw Exception("Nội dung bình luận không được để trống!")
        }
        val user = firestore.collection("users")
            .document(currentUser.uid).get().await()
        val displayName = user.getString("displayName") ?: "Độc Giả Bookly"
        val photoUrl = user.getString("photoUrl") ?: currentUser.photoUrl?.toString()
        val commentData = hashMapOf(
            "userId" to currentUser.uid,
            "userName" to displayName,
            "userAvatar" to photoUrl,
            "content" to comment.trim(),
            "createdAt" to System.currentTimeMillis()
        )
        firestore.collection("books")
            .document(bookId)
            .collection("chapters")
            .document(chapterId)
            .collection("comments")
            .add(commentData).await()
        Unit
    }

    override suspend fun deleteComment(
        bookId: String,
        chapterId: String,
        commentId: String
    ): Result<Unit> = runCatching {
        val currentUser = auth.currentUser ?: throw Exception("Vui lòng đăng nhập!")
        val commentRef = firestore.collection("books")
            .document(bookId)
            .collection("chapters")
            .document(chapterId)
            .collection("comments")
            .document(commentId)
        val doc = commentRef.get().await()
        val ownerId = doc.getString("userId")
        if (ownerId != currentUser.uid) {
            throw Exception("Bạn không có quyền xóa bình luận này!")
        }
        commentRef.delete().await()
        Unit
    }
}
