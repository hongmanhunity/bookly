package com.example.bookly.data.seeder

import com.example.bookly.domain.model.Book
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

/**
 * Công cụ gieo dữ liệu mẫu (Data Seeder) cho ứng dụng Bookly.
 * Tự động đẩy danh mục sách mẫu phong phú lên Cloud Firestore chỉ với 1 lệnh gọi.
 */
object FirestoreSeeder {

    suspend fun seedBooks(
        firestore: FirebaseFirestore = FirebaseFirestore.getInstance(),
        forceReSeed: Boolean = true
    ): Result<Unit> {
        return runCatching {
            val collection = firestore.collection("books")

            val snapshot = collection.get().await()
            if (snapshot.documents.size < 10 || forceReSeed) {
                // Xoá các document cũ nếu chưa đủ 10 cuốn sách
                for (doc in snapshot.documents) {
                    doc.reference.delete().await()
                }
            } else {
                return@runCatching
            }

            val sampleBooks = listOf(
                Book(
                    title = "Atomic Habits - Thay Đổi Tí Hon Hiệu Quả Bất Ngờ",
                    author = "James Clear",
                    description = "Phương pháp thực tế giúp bạn xây dựng thói quen tốt, loại bỏ thói quen xấu và gặt hái thành quả vượt bậc mỗi ngày.",
                    category = "Kỹ năng sống",
                    coverUrl = "https://covers.openlibrary.org/b/id/12885623-L.jpg",
                    rating = 4.9,
                    pageCount = 320,
                    publishedYear = 2018
                ),
                Book(
                    title = "Nhà Giả Kim",
                    author = "Paulo Coelho",
                    description = "Hành trình theo đuổi vận mệnh của chàng chăn cừu Santiago với những bài học sâu sắc về ước mơ, lòng dũng cảm và mục đích sống.",
                    category = "Tiểu thuyết",
                    coverUrl = "https://covers.openlibrary.org/b/id/12833521-L.jpg",
                    rating = 4.9,
                    pageCount = 224,
                    publishedYear = 1988
                ),
                Book(
                    title = "Tâm Lý Học Về Tiền",
                    author = "Morgan Housel",
                    description = "19 câu chuyện ngụ ngôn tuyệt vời khám phá cách con người tư duy về tiền bạc, sự giàu có, lòng tham và hạnh phúc cá nhân.",
                    category = "Kinh doanh",
                    coverUrl = "https://covers.openlibrary.org/b/id/10556676-L.jpg",
                    rating = 4.8,
                    pageCount = 256,
                    publishedYear = 2020
                ),
                Book(
                    title = "Hoàng Tử Bé",
                    author = "Antoine de Saint-Exupéry",
                    description = "Câu chuyện triết lý nhẹ nhàng về tình bạn, tình yêu và góc nhìn ngây thơ nhưng sâu sắc của một cậu bé đến từ hành tinh B-612.",
                    category = "Tiểu thuyết",
                    coverUrl = "https://covers.openlibrary.org/b/id/10522194-L.jpg",
                    rating = 4.9,
                    pageCount = 140,
                    publishedYear = 1943
                ),
                Book(
                    title = "Cây Cam Ngọt Của Tôi",
                    author = "José Mauro de Vasconcelos",
                    description = "Tác phẩm cảm động về tuổi thơ nghèo khó nhưng giàu trí tưởng tượng của cậu bé Zezé cùng người bạn tri kỷ là cây cam ngọt trong vườn.",
                    category = "Văn học",
                    coverUrl = "https://covers.openlibrary.org/b/id/12025732-L.jpg",
                    rating = 4.9,
                    pageCount = 244,
                    publishedYear = 1968
                ),
                Book(
                    title = "Dune - Xứ Cát",
                    author = "Frank Herbert",
                    description = "Kiệt tác viễn tưởng về hành tinh sa mạc Arrakis, cuộc chiến tranh giành nguồn gia vị quý hiếm và số phận epic của Paul Atreides.",
                    category = "Khoa học viễn tưởng",
                    coverUrl = "https://covers.openlibrary.org/b/id/10603774-L.jpg",
                    rating = 4.8,
                    pageCount = 688,
                    publishedYear = 1965
                ),
                Book(
                    title = "Clean Code - Mã Sạch",
                    author = "Robert C. Martin",
                    description = "Cuốn sách kinh điển hướng dẫn các nguyên tắc viết mã nguồn sạch, tinh gọn, dễ đọc và bảo trì cho lập trình viên chuyên nghiệp.",
                    category = "Công nghệ",
                    coverUrl = "https://covers.openlibrary.org/b/id/9628476-L.jpg",
                    rating = 4.8,
                    pageCount = 464,
                    publishedYear = 2008
                ),
                Book(
                    title = "Tư Duy Nhanh Và Chậm",
                    author = "Daniel Kahneman",
                    description = "Khám phá 2 hệ thống tư duy chi phối nhận thức và quyết định của con người, giải mã những định kiến và sai lầm hệ thống trong suy nghĩ.",
                    category = "Tâm lý học",
                    coverUrl = "https://covers.openlibrary.org/b/id/12693892-L.jpg",
                    rating = 4.7,
                    pageCount = 499,
                    publishedYear = 2011
                ),
                Book(
                    title = "Đắc Nhân Tâm",
                    author = "Dale Carnegie",
                    description = "Nghệ thuật thu phục lòng người, ứng xử trong giao tiếp và xây dựng mối quan hệ cá nhân lẫn công việc vô cùng thành công.",
                    category = "Kỹ năng sống",
                    coverUrl = "https://covers.openlibrary.org/b/id/12825625-L.jpg",
                    rating = 4.9,
                    pageCount = 320,
                    publishedYear = 1936
                ),
                Book(
                    title = "Harry Potter và Hòn Đá Phù Thủy",
                    author = "J.K. Rowling",
                    description = "Khám phá thế giới phép thuật kỳ diệu tại trường Hogwarts cùng cậu bé Harry Potter và những người bạn thân thiết.",
                    category = "Văn học",
                    coverUrl = "https://covers.openlibrary.org/b/id/10521270-L.jpg",
                    rating = 4.9,
                    pageCount = 309,
                    publishedYear = 1997
                )
            )

            // Đẩy từng cuốn sách lên Firestore
            for (book in sampleBooks) {
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
                collection.add(dataMap).await()
            }
        }
    }
}
