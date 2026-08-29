package com.example.bookly.domain.model

import java.util.Date

data class User(
    val uid: String = "",
    val email: String = "",
    val displayName: String = "",
    val photoUrl: String? = null,
    val bio: String = "",
    val role: String = "USER", // "USER" hoặc "ADMIN"

    // Dữ liệu cá nhân hóa cho ứng dụng sách
    val favoriteBookIds: List<String> = emptyList(),
    val currentlyReadingIds: List<String> = emptyList(),
    val finishedBookIds: List<String> = emptyList(),

    val createdAt: Date? = null
)