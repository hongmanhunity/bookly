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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookly.domain.model.User
import com.example.bookly.ui.components.common.ErrorStateView
import com.example.bookly.ui.components.common.LoadingStateView
import com.example.bookly.ui.state.ProfileUiState
import com.example.bookly.ui.theme.BooklyGreenPrimary
import com.example.bookly.ui.viewmodel.ProfileViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel(),
    onLogoutClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when (val state = uiState) {
            is ProfileUiState.Loading -> {
                LoadingStateView(message = "Đang tải thông tin cá nhân...")
            }
            is ProfileUiState.Error -> {
                ErrorStateView(message = state.message)
            }
            is ProfileUiState.Success -> {
                ProfileContent(
                    user = state.user,
                    onLogoutClick = {
                        viewModel.logout(onLogoutSuccess = onLogoutClick)
                    }
                )
            }
        }
    }
}

@Composable
private fun ProfileContent(
    user: User,
    onLogoutClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileHeaderSection(user = user)
        Spacer(modifier = Modifier.height(28.dp))

        ReadingStatsSection(user = user)
        Spacer(modifier = Modifier.height(28.dp))

        AccountDetailsSection(user = user)
        Spacer(modifier = Modifier.height(36.dp))

        LogoutButton(onLogoutClick = onLogoutClick)
    }
}

@Composable
private fun ProfileHeaderSection(user: User) {
    val firstLetter = user.displayName.firstOrNull()?.uppercase() ?: "B"

    Box(
        modifier = Modifier
            .size(96.dp)
            .clip(CircleShape)
            .background(BooklyGreenPrimary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = firstLetter,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }

    Spacer(modifier = Modifier.height(14.dp))

    Text(
        text = user.displayName.ifBlank { "Người dùng Bookly" },
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface
    )

    if (user.bio.isNotBlank()) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "\"${user.bio}\"",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
private fun ReadingStatsSection(user: User) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        StatItem(
            count = user.currentlyReadingIds.size,
            label = "Đang đọc",
            icon = Icons.Default.AutoStories,
            color = Color(0xFF3B82F6)
        )

        Box(
            modifier = Modifier
                .width(1.dp)
                .height(32.dp)
                .background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))
        )

        StatItem(
            count = user.finishedBookIds.size,
            label = "Đã xong",
            icon = Icons.Default.CheckCircle,
            color = BooklyGreenPrimary
        )

        Box(
            modifier = Modifier
                .width(1.dp)
                .height(32.dp)
                .background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))
        )

        StatItem(
            count = user.favoriteBookIds.size,
            label = "Yêu thích",
            icon = Icons.Default.Favorite,
            color = Color(0xFFEF4444)
        )
    }
}

@Composable
private fun AccountDetailsSection(user: User) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Thông tin tài khoản",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(14.dp))

        ProfileInfoRow(
            icon = Icons.Default.Email,
            title = "Email",
            value = user.email.ifBlank { "Chưa có email" }
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 10.dp),
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
        )

        val formattedDate = user.createdAt?.let {
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(it)
        } ?: "Mới tham gia"

        ProfileInfoRow(
            icon = Icons.Default.CalendarMonth,
            title = "Ngày tham gia",
            value = formattedDate
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 10.dp),
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
        )

        ProfileInfoRow(
            icon = Icons.Default.Info,
            title = "Phiên bản ứng dụng",
            value = "Bookly v1.0.0"
        )
    }
}

@Composable
private fun LogoutButton(onLogoutClick: () -> Unit) {
    Button(
        onClick = onLogoutClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFEE2E2)
        )
    ) {
        Icon(
            imageVector = Icons.Default.Logout,
            contentDescription = null,
            tint = Color(0xFFDC2626),
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Đăng xuất tài khoản",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFFDC2626)
        )
    }
}

@Composable
private fun StatItem(
    count: Int,
    label: String,
    icon: ImageVector,
    color: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = count.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProfileInfoRow(
    icon: ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BooklyGreenPrimary,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = title,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
