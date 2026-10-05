package com.example.bookly.domain.model

data class Book(
    val id: String = "",
    val title: String = "",
    val author: String = "",
    val description: String = "",
    val category: String = "",
    val coverUrl: String = "",
    val rating: Double = 0.0,
    val pageCount: Int = 0,
    val publishedYear: Int = 2024
)
