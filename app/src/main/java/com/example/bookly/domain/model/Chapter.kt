package com.example.bookly.domain.model

data class Chapter(
    val id: String = "",
    val bookId: String = "",
    val chapterNumber: Int = 1,
    val title: String = "",
    val content: String = ""
)
