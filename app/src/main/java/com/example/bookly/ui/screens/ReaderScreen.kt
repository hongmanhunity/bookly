package com.example.bookly.ui.screens

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookly.ui.components.common.ErrorStateView
import com.example.bookly.ui.components.common.LoadingStateView
import com.example.bookly.ui.state.DetailBookUiState
import com.example.bookly.ui.theme.BooklyGreenPrimary
import com.example.bookly.ui.viewmodel.BookDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ReaderScreen(
    bookId: String,
    initialChapterNumber: Int = 1,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookDetailViewModel = koinViewModel()
) {
    LaunchedEffect(bookId) {
        viewModel.loadBookDetail(bookId)
    }

    val uiState by viewModel.uiState.collectAsState()
    var currentChapterNum by remember(initialChapterNumber) { mutableIntStateOf(initialChapterNumber) }
    var fontSizeSp by remember { mutableIntStateOf(16) }
    val scrollState = rememberScrollState()

    LaunchedEffect(currentChapterNum) {
        scrollState.scrollTo(0)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = uiState) {
            is DetailBookUiState.Loading -> {
                LoadingStateView(message = "Đang chuẩn bị trang đọc...")
            }

            is DetailBookUiState.Error -> {
                ErrorStateView(message = state.message)
            }

            is DetailBookUiState.Success -> {
                val book = state.book
                val chapters = state.chapters
                val currentChapter = chapters.find { it.chapterNumber == currentChapterNum }
                    ?: chapters.getOrNull(currentChapterNum - 1)

                Column(modifier = modifier.fillMaxSize()) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth(),
                        tonalElevation = 4.dp,
                        shadowElevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = onBackClick) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Quay lại"
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = book.title,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = currentChapter?.title ?: "Chương $currentChapterNum",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = BooklyGreenPrimary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    onClick = { if (fontSizeSp > 12) fontSizeSp -= 2 },
                                    enabled = fontSizeSp > 12
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Remove,
                                        contentDescription = "Giảm cỡ chữ",
                                        modifier = Modifier.clip(CircleShape)
                                    )
                                }
                                Text(
                                    text = "${fontSizeSp}sp",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                IconButton(
                                    onClick = { if (fontSizeSp < 24) fontSizeSp += 2 },
                                    enabled = fontSizeSp < 24
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Add,
                                        contentDescription = "Tăng cỡ chữ"
                                    )
                                }
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .verticalScroll(scrollState)
                    ) {
                        Column(modifier = Modifier.padding(vertical = 20.dp)) {
                            Text(
                                text = currentChapter?.title ?: "Chương $currentChapterNum",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            val contentText = currentChapter?.content
                                ?: "Nội dung chương này đang được cập nhật..."

                            Text(
                                text = contentText,
                                fontSize = fontSizeSp.sp,
                                lineHeight = (fontSizeSp * 1.65).sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Justify
                            )

                            Spacer(modifier = Modifier.height(40.dp))
                        }
                    }

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth(),
                        tonalElevation = 6.dp,
                        shadowElevation = 8.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedButton(
                                onClick = { if (currentChapterNum > 1) currentChapterNum-- },
                                enabled = currentChapterNum > 1,
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.padding(2.dp))
                                Text("Trước")
                            }

                            Text(
                                text = "Chương $currentChapterNum / ${chapters.size.coerceAtLeast(1)}",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Button(
                                onClick = { if (currentChapterNum < chapters.size) currentChapterNum++ },
                                enabled = currentChapterNum < chapters.size,
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = BooklyGreenPrimary)
                            ) {
                                Text("Sau")
                                Spacer(modifier = Modifier.padding(2.dp))
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
