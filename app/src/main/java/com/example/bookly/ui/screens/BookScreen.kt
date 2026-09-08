package com.example.bookly.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookly.ui.components.book.BookCard
import com.example.bookly.ui.components.book.SearchBar
import com.example.bookly.ui.components.common.ErrorStateView
import com.example.bookly.ui.components.common.LoadingStateView
import com.example.bookly.ui.state.BookUiState
import com.example.bookly.ui.viewmodel.BookViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookScreen(
    modifier: Modifier = Modifier,
    onBookClick: (String) -> Unit,
    viewModel: BookViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        when (val state = uiState) {
            is BookUiState.Loading -> {
                LoadingStateView(message = "Đang tìm kiếm sách...")
            }

            is BookUiState.Error -> {
                ErrorStateView(message = state.message)
            }

            is BookUiState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    item(span = { GridItemSpan(2) }) {
                        SearchBar(
                            query = searchQuery,
                            onQueryChange = viewModel::onSearchQueryChange,
                            onClear = viewModel::clearQuery
                        )
                    }

                    if (state.books.isEmpty()) {
                        item(span = { GridItemSpan(2) }) {
                            EmptySearchResult(searchQuery = searchQuery)
                        }
                    } else {
                        items(state.books) { book ->
                            BookCard(
                                book = book,
                                onBookClick = onBookClick
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptySearchResult(searchQuery: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.SearchOff,
            contentDescription = null,
            tint = Color.Gray.copy(alpha = 0.5f),
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Không tìm thấy sách nào",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = if (searchQuery.isBlank())
                "Chưa có cuốn sách nào trong thư viện"
            else
                "Không có cuốn sách nào khớp với từ khóa \"$searchQuery\"",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}
