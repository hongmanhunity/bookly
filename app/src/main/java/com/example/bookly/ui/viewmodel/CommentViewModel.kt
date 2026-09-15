package com.example.bookly.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.domain.repository.CommentRepository
import com.example.bookly.ui.state.CommentUiState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CommentViewModel(
    private val commentRepository: CommentRepository,
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) : ViewModel() {

    private val _uiState = MutableStateFlow<CommentUiState>(CommentUiState.Loading)
    val uiState: StateFlow<CommentUiState> = _uiState.asStateFlow()

    val currentUserId: String
        get() = auth.currentUser?.uid ?: ""

    fun loadComments(bookId: String, chapterId: String) {
        _uiState.value = CommentUiState.Loading
        viewModelScope.launch {
            commentRepository.getComments(bookId, chapterId)
                .catch { err ->
                    _uiState.value = CommentUiState.Error(err.message ?: "Lỗi tải bình luận")
                }
                .collect { comments ->
                    _uiState.value = CommentUiState.Success(comments)
                }
        }
    }

    fun sendComment(
        bookId: String,
        chapterId: String,
        content: String,
        onSuccess: () -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        if (content.trim().isEmpty()) return

        viewModelScope.launch {
            val result = commentRepository.sendComment(bookId, chapterId, content)
            result.onSuccess {
                onSuccess()
            }.onFailure { err ->
                onError(err.message ?: "Không thể gửi bình luận")
            }
        }
    }

    fun deleteComment(
        bookId: String,
        chapterId: String,
        commentId: String,
        onError: (String) -> Unit = {}
    ) {
        viewModelScope.launch {
            val result = commentRepository.deleteComment(bookId, chapterId, commentId)
            result.onFailure { err ->
                onError(err.message ?: "Lỗi khi xóa bình luận")
            }
        }
    }
}