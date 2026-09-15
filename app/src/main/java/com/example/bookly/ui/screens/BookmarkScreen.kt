package com.example.bookly.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookly.domain.model.Book
import com.example.bookly.ui.components.book.BookCard
import com.example.bookly.ui.theme.BooklyGreenPrimary
import com.example.bookly.ui.viewmodel.BookmarkStats
import com.example.bookly.ui.viewmodel.BookmarkViewModel
import org.koin.androidx.compose.koinViewModel
import kotlin.math.roundToInt

@Composable
fun BookmarkScreen(
    onBookClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    viewModel: BookmarkViewModel = koinViewModel()
) {
    val bookmarks by viewModel.bookmarks.collectAsState()
    val filteredBookmarks by viewModel.filteredBookmarks.collectAsState()
    val stats by viewModel.stats.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // 1. Thanh tiêu đề Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (onBackClick != null) {
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
            }
            Column(modifier = Modifier.padding(start = if (onBackClick != null) 10.dp else 0.dp)) {
                Text(
                    text = "Sách đã lưu (${bookmarks.size})",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        // 2. Nội dung chính
        if (bookmarks.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.BookmarkBorder,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                        modifier = Modifier.size(72.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Bạn chưa lưu cuốn sách nào",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                // Khối Thống Kê Chi Tiết
                item(span = { GridItemSpan(2) }) {
                    FavoriteStatsCard(stats = stats)
                }

                // Bộ Lọc Thể Loại
                if (stats.categoryDistribution.size > 1) {
                    item(span = { GridItemSpan(2) }) {
                        CategoryFilterSection(
                            categories = stats.categoryDistribution.map { it.category },
                            selectedCategory = selectedCategory,
                            onCategorySelect = viewModel::onCategorySelect,
                            totalCount = bookmarks.size
                        )
                    }
                }

                // Danh sách sách yêu thích dạng lưới
                items(filteredBookmarks, key = { it.bookId }) { item ->
                    val book = Book(
                        id = item.bookId,
                        title = item.title,
                        author = item.author,
                        coverUrl = item.coverUrl,
                        category = item.category,
                        rating = item.rating
                    )
                    BookCard(
                        book = book,
                        onBookClick = onBookClick
                    )
                }
            }
        }
    }
}

@Composable
private fun FavoriteStatsCard(stats: BookmarkStats) {
    val percentFinished = (stats.completionRate * 100).roundToInt()
    val total = stats.totalBooks.coerceAtLeast(1)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp)
    ) {
        // 1. Header: Tiêu đề & Huy hiệu % hoàn thành
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(BooklyGreenPrimary.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Bookmark,
                        contentDescription = null,
                        tint = BooklyGreenPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Tiến độ đọc tủ sách",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Surface(
                shape = RoundedCornerShape(8.dp),
                color = BooklyGreenPrimary.copy(alpha = 0.12f)
            ) {
                Text(
                    text = "$percentFinished% Hoàn thành",
                    color = BooklyGreenPrimary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 2. Segmented Reading Progress Bar (Thanh tiến độ phân đoạn)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(7.dp)
                .clip(RoundedCornerShape(3.5.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
        ) {
            if (stats.finishedCount > 0) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(stats.finishedCount.toFloat() / total)
                        .background(BooklyGreenPrimary)
                )
            }
            if (stats.readingCount > 0) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(stats.readingCount.toFloat() / total)
                        .background(Color(0xFF3B82F6))
                )
            }
            if (stats.unreadCount > 0) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(stats.unreadCount.toFloat() / total)
                        .background(Color(0xFFF59E0B).copy(alpha = 0.65f))
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 3. Ba hộp chỉ số hành động (Đang đọc, Đã xong, Chưa đọc)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ReadingMetricBox(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.AutoStories,
                iconColor = Color(0xFF3B82F6),
                count = stats.readingCount,
                label = "Đang đọc"
            )
            ReadingMetricBox(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.CheckCircle,
                iconColor = BooklyGreenPrimary,
                count = stats.finishedCount,
                label = "Đã xong"
            )
            ReadingMetricBox(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Schedule,
                iconColor = Color(0xFFF59E0B),
                count = stats.unreadCount,
                label = "Chưa đọc"
            )
        }
    }
}

@Composable
private fun ReadingMetricBox(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconColor: Color,
    count: Int,
    label: String
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "$count",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = label,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CategoryFilterSection(
    categories: List<String>,
    selectedCategory: String?,
    onCategorySelect: (String?) -> Unit,
    totalCount: Int
) {
    Column {
        Text(
            text = "Lọc theo thể loại",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            item {
                FilterChip(
                    selected = selectedCategory == null,
                    onClick = { onCategorySelect(null) },
                    label = { Text("Tất cả ($totalCount)", fontSize = 12.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = BooklyGreenPrimary,
                        selectedLabelColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                        labelColor = MaterialTheme.colorScheme.onSurface
                    ),
                    border = null,
                    shape = RoundedCornerShape(12.dp)
                )
            }
            items(categories) { category ->
                val isSelected = selectedCategory == category
                FilterChip(
                    selected = isSelected,
                    onClick = { onCategorySelect(category) },
                    label = { Text(category, fontSize = 12.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = BooklyGreenPrimary,
                        selectedLabelColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                        labelColor = MaterialTheme.colorScheme.onSurface
                    ),
                    border = null,
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }
    }
}
