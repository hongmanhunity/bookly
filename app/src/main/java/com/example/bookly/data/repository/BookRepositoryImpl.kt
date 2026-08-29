package com.example.bookly.data.repository

import com.example.bookly.domain.model.Book
import com.example.bookly.domain.repository.BookRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class BookRepositoryImpl(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) : BookRepository {
    override fun getBooks(): Flow<List<Book>> = callbackFlow {
        val listener = firestore.collection("books")
            .addSnapshotListener { snapshots, exception ->
                if (exception != null) {
                    close(exception)
                    return@addSnapshotListener
                }
                val books = snapshots?.documents?.map { document ->
                    Book(
                        id = document.id,
                        title = document.getString("title") ?: "",
                        author = document.getString("author") ?: "",
                        description = document.getString("description") ?: "",
                        category = document.getString("category") ?: "",
                        coverUrl = document.getString("coverUrl") ?: "",
                        rating = document.getDouble("rating") ?: 0.0,
                        pageCount = document.getLong("pageCount")?.toInt() ?: 0,
                        publishedYear = document.getLong("publishedYear")?.toInt() ?: 2024
                    )
                } ?: emptyList()

                trySend(books)
            }

        awaitClose { listener.remove() }
    }

    override fun getBookById(bookID: String): Flow<Book> = callbackFlow {
        val listener = firestore.collection("books")
            .document(bookID)
            .addSnapshotListener { document, exception ->
                if (exception != null || document == null || !document.exists()) return@addSnapshotListener
                val book = Book(
                    id = document.id,
                    title = document.getString("title") ?: "",
                    author = document.getString("author") ?: "",
                    description = document.getString("description") ?: "",
                    category = document.getString("category") ?: "",
                    coverUrl = document.getString("coverUrl") ?: "",
                    rating = document.getDouble("rating") ?: 0.0,
                    pageCount = document.getLong("pageCount")?.toInt() ?: 0,
                    publishedYear = document.getLong("publishedYear")?.toInt() ?: 2024
                )

                trySend(book)
            }

        awaitClose { listener.remove() }
    }

    override suspend fun seedSampleBooks(): Result<Unit> {
        return com.example.bookly.data.seeder.FirestoreSeeder.seedBooks(firestore)
    }
}