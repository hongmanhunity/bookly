package com.example.bookly.ui.state

import com.example.bookly.domain.model.Comment

sealed class CommentUiState {
    data object Loading : CommentUiState()
    data class Success(val comments: List<Comment>) : CommentUiState()
    data class Error(val message: String) : CommentUiState()
}