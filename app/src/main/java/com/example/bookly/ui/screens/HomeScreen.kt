package com.example.bookly.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookly.ui.components.common.ErrorStateView
import com.example.bookly.ui.components.common.LoadingStateView
import com.example.bookly.ui.components.common.SectionHeader
import com.example.bookly.ui.components.home.NewReleaseBookCard
import com.example.bookly.ui.components.home.RecommendedBookRow
import com.example.bookly.ui.components.home.TrendingBookCard
import com.example.bookly.ui.state.HomeUiState
import com.example.bookly.ui.theme.BooklyGreenPrimary
import com.example.bookly.ui.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onBookClick: (String) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when (val state = uiState) {
            is HomeUiState.Loading -> {
                LoadingStateView(message = "Đang tải trang chủ...")
            }

            is HomeUiState.Error -> {
                ErrorStateView(message = state.message)
            }

            is HomeUiState.Success -> {
                val trendingBooks = state.trendingBooks
                val newReleases = state.newReleases
                val featuredBooks = state.featuredBooks

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .verticalScroll(rememberScrollState())
                ) {

                    SectionHeader(
                        title = "Light Novel Thịnh Hành",
                        icon = Icons.Default.LocalFireDepartment,
                        iconTint = Color(0xFFFF5722)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        contentPadding = PaddingValues(bottom = 8.dp)
                    ) {
                        items(trendingBooks) { book ->
                            TrendingBookCard(
                                book = book,
                                onClick = { onBookClick(book.id) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    if (newReleases.isNotEmpty()) {
                        SectionHeader(
                            title = "Mới Cập Nhật Ra Mắt",
                            icon = Icons.Default.AutoAwesome,
                            iconTint = Color(0xFF00B0FF)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                            contentPadding = PaddingValues(bottom = 8.dp)
                        ) {
                            items(newReleases) { book ->
                                NewReleaseBookCard(
                                    book = book,
                                    onClick = { onBookClick(book.id) }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(28.dp))
                    }

                    SectionHeader(
                        title = "Gợi Ý Cho Bạn",
                        icon = Icons.AutoMirrored.Filled.TrendingUp,
                        iconTint = BooklyGreenPrimary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    featuredBooks.forEachIndexed { index, book ->
                        RecommendedBookRow(
                            book = book,
                            onClick = { onBookClick(book.id) }
                        )
                        if (index < featuredBooks.size - 1) {
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 6.dp),
                                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}
