package com.example.bookly.data.seeder

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

/**
 * Điều phối dữ liệu mẫu (Data Seeder Orchestrator) cho ứng dụng Bookly.
 * Tổng hợp 8 file dữ liệu Light Novel riêng biệt, mỗi bộ chứa 10 chương đầy đủ.
 */
object FirestoreSeeder {

    suspend fun seedBooks(
        firestore: FirebaseFirestore = FirebaseFirestore.getInstance(),
        forceReSeed: Boolean = false
    ): Result<Unit> {
        return runCatching {
            val collection = firestore.collection("books")

            val snapshot = collection.get().await()
            if (snapshot.documents.isNotEmpty() && !forceReSeed) {
                // Đã có dữ liệu trong Firestore và forceReSeed = false -> Không nạp lại
                return@runCatching
            }

            if (forceReSeed) {
                // Xoá các document cũ để gieo lại dữ liệu
                for (doc in snapshot.documents) {
                    doc.reference.delete().await()
                }
            }

            // Danh sách 8 bộ Light Novel được mô-đun hóa thành 8 file dữ liệu riêng biệt
            val sampleLightNovels = listOf(
                ClassroomOfTheEliteData.bookData,
                SwordArtOnlineData.bookData,
                EightySixData.bookData,
                DateALiveData.bookData,
                SoloLevelingData.bookData,
                MushokuTenseiData.bookData,
                NoGameNoLifeData.bookData,
                SlimeDattaKenData.bookData
            )

            // Đẩy từng bộ Light Novel kèm Sub-collection Chapters chuẩn gốc lên Firestore
            for ((book, chapters) in sampleLightNovels) {
                val dataMap = mapOf(
                    "title" to book.title,
                    "author" to book.author,
                    "description" to book.description,
                    "category" to book.category,
                    "coverUrl" to book.coverUrl,
                    "rating" to book.rating,
                    "pageCount" to book.pageCount,
                    "publishedYear" to book.publishedYear
                )
                val bookDocRef = collection.add(dataMap).await()

                // Đẩy 10 chương tương ứng vào sub-collection "chapters"
                val chaptersCollection = bookDocRef.collection("chapters")
                chapters.forEachIndexed { index, (chapterTitle, chapterContent) ->
                    val chapterData = mapOf(
                        "chapterNumber" to (index + 1),
                        "title" to chapterTitle,
                        "content" to chapterContent
                    )
                    chaptersCollection.add(chapterData).await()
                }
            }
        }
    }
}
