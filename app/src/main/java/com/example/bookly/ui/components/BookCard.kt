package com.example.bookly.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.bookly.domain.model.Book

@Composable
fun BookCard(
    books: Book,
    onBookClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onBookClick(books.id) }
    ) {
        // Bìa sách thiết kế kiểu Floating Poster nổi với bóng đổ mềm mịn
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            shadowElevation = 6.dp,
            color = Color.White
        ) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(books.coverUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = books.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.68f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFE8ECEF)),
                    contentScale = ContentScale.Crop
                )

                // Badge Đánh giá (Rating) góc trên bên phải
                if (books.rating > 0) {
                    Surface(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp),
                        shape = RoundedCornerShape(8.dp),
                        color = Color.White.copy(alpha = 0.9f),
                        shadowElevation = 2.dp
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "⭐ ${books.rating}",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2C3E50)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Tên sách (Chữ màu tối sang trọng)
        Text(
            text = books.title,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF2C3E50),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(2.dp))

        // Tên tác giả (Màu xám thanh lịch)
        Text(
            text = books.author,
            fontSize = 13.sp,
            color = Color(0xFF7F8C8D),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
