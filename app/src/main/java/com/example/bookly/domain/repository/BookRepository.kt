package com.example.bookly.domain.repository

import com.example.bookly.domain.model.Book
import com.example.bookly.domain.model.Chapter
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getBooks(): Flow<List<Book>>
    fun getBookById(bookID: String): Flow<Book>
    fun getChapters(bookId: String): Flow<List<Chapter>>
    fun getFeaturedBooks(): Flow<List<Book>>
    fun getTrendingBooks(): Flow<List<Book>>
}