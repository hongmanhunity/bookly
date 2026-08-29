package com.example.bookly.domain.repository

import com.example.bookly.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getBooks(): Flow<List<Book>>
    fun getBookById(bookID: String): Flow<Book>
    suspend fun seedSampleBooks(): Result<Unit>
}