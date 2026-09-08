package com.example.bookly.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.bookly.ui.components.common.ErrorStateView
import com.example.bookly.ui.components.common.LoadingStateView
import com.example.bookly.ui.components.detail.ChapterItemRow
import com.example.bookly.ui.components.detail.DetailStatItem
import com.example.bookly.ui.state.DetailBookUiState
import com.example.bookly.ui.theme.BooklyGreenPrimary
import com.example.bookly.ui.viewmodel.BookDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookDetailScreen(
    bookId: String,
    onBackClick: () -> Unit,
    onChapterClick: (chapterNumber: Int) -> Unit = {},
    viewModel: BookDetailViewModel = koinViewModel(),

    ) {
    LaunchedEffect(bookId) {
        viewModel.loadBookDetail(bookId)
    }

    val rawUiState by viewModel.uiState.collectAsState()
    
    val uiState = if (rawUiState is DetailBookUiState.Success && (rawUiState as DetailBookUiState.Success).book.id != bookId) {
        DetailBookUiState.Loading
    } else {
        rawUiState
    }

    // ✅ MỚI: Đọc trực tiếp trạng thái từ Room Database:
    val isFavorite by viewModel.isBookmarked(bookId).collectAsState(initial = false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        // 1. Top Navigation Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Quay lại"
                )
            }
            Text(
                text = "Chi tiết sách",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            IconButton(
                onClick = {
                    if (uiState is DetailBookUiState.Success) {
                        val currentBook = (uiState as DetailBookUiState.Success).book
                        viewModel.toggleBookmark(currentBook, isFavorite)
                    }
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                    contentDescription = "Bookmark",
                    tint = if (isFavorite) BooklyGreenPrimary else MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 2. Nội dung Chi tiết Sách hoặc Loading tinh tế
        when (val state = uiState) {
            is DetailBookUiState.Loading -> {
                LoadingStateView(message = "Đang tải thông tin sách...")
            }

            is DetailBookUiState.Error -> {
                ErrorStateView(message = state.message)
            }

            is DetailBookUiState.Success -> {
                val book = state.book
                val chapters = state.chapters

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Cover Image Hero
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        shadowElevation = 12.dp,
                        tonalElevation = 4.dp
                    ) {
                        val context = LocalContext.current
                        val imageRequest = remember(book.coverUrl) {
                            ImageRequest.Builder(context)
                                .data(book.coverUrl)
                                .crossfade(true)
                                .build()
                        }

                        AsyncImage(
                            model = imageRequest,
                            contentDescription = book.title,
                            modifier = Modifier
                                .width(190.dp)
                                .height(275.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color(0xFFE8ECEF)),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Title & Author
                    Text(
                        text = book.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Tác giả: ${book.author}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Quick Stats Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DetailStatItem(
                            icon = Icons.Filled.Star,
                            value = "${book.rating}",
                            label = "Đánh giá",
                            iconTint = Color(0xFFFFB800)
                        )
                        StatVerticalDivider()
                        DetailStatItem(
                            icon = Icons.AutoMirrored.Filled.MenuBook,
                            value = book.category,
                            label = "Thể loại",
                            iconTint = BooklyGreenPrimary
                        )
                        StatVerticalDivider()
                        DetailStatItem(
                            icon = Icons.AutoMirrored.Filled.Article,
                            value = "${chapters.size} chương",
                            label = "Đã phát hành",
                            iconTint = MaterialTheme.colorScheme.primary
                        )
                        StatVerticalDivider()
                        DetailStatItem(
                            icon = Icons.Filled.CalendarToday,
                            value = "${book.publishedYear}",
                            label = "Năm XB",
                            iconTint = MaterialTheme.colorScheme.secondary
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Nút Đọc Sách
                    Button(
                        onClick = {
                            if (chapters.isNotEmpty()) {
                                onChapterClick(1)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BooklyGreenPrimary)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.MenuBook,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Bắt Đầu Đọc",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Giới Thiệu Nội Dung
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Giới Thiệu Tác Phẩm",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = book.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 22.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Danh Sách Chương
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Danh Sách Chương (${chapters.size})",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        if (chapters.isEmpty()) {
                            Text(
                                text = "Chưa có chương nào cho cuốn sách này.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        } else {
                            chapters.forEachIndexed { index, chapter ->
                                ChapterItemRow(
                                    chapter = chapter,
                                    onClick = { onChapterClick(chapter.chapterNumber) }
                                )
                                if (index < chapters.size - 1) {
                                    HorizontalDivider(
                                        modifier = Modifier.padding(vertical = 4.dp),
                                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
private fun StatVerticalDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(28.dp)
            .background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
    )
}
