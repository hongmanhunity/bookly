package com.example.bookly.domain.model

data class Comment(
    val id: String = "",
    val userId: String = "",
    val userName: String = "",
    val userAvatar: String? = null,
    val content: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
