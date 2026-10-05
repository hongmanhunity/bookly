package com.example.bookly.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.bookly.ui.components.common.BooklyConfirmationDialog
import com.example.bookly.ui.components.common.ErrorStateView
import com.example.bookly.ui.components.common.LoadingStateView
import com.example.bookly.ui.state.ProfileUiState
import com.example.bookly.ui.theme.BooklyGreenPrimary
import com.example.bookly.ui.viewmodel.BookmarkViewModel
import com.example.bookly.ui.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
    onLogoutClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val bookmarkCount by viewModel.bookmarkCount.collectAsState()
    val currentlyReadingCount by viewModel.currentlyReadingCount.collectAsState()
    val finishedReadingCount by viewModel.finishedReadingCount.collectAsState()
    val isDarkMode by viewModel.isDarkMode.collectAsState()

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
                    bookmarkCount = bookmarkCount,
                    currentlyReadingCount = currentlyReadingCount,
                    finishedReadingCount = finishedReadingCount,
                    isDarkMode = isDarkMode,
                    onToggleDarkMode = viewModel::toggleDarkMode,
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
    bookmarkCount: Int,
    currentlyReadingCount: Int,
    finishedReadingCount: Int,
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit,
    onLogoutClick: () -> Unit
) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    if (showLogoutDialog) {
        BooklyConfirmationDialog(
            title = "Đăng xuất tài khoản?",
            message = "Bạn có chắc muốn đăng xuất?\nĐăng nhập lại để tiếp tục đọc.",
            confirmText = "Đăng xuất",
            dismissText = "Ở lại",
            icon = Icons.AutoMirrored.Filled.Logout,
            isDestructive = true,
            onConfirm = {
                showLogoutDialog = false
                onLogoutClick()
            },
            onDismiss = {
                showLogoutDialog = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
            ProfileHeaderSection(user = user)

            Spacer(modifier = Modifier.height(12.dp))

            ReadingStatsSection(
                user = user,
                bookmarkCount = bookmarkCount,
                currentlyReadingCount = currentlyReadingCount,
                finishedReadingCount = finishedReadingCount
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                AccountDetailsSection(user = user)
                Spacer(modifier = Modifier.height(14.dp))
                ThemeSettingSection(
                    isDarkMode = isDarkMode,
                    onToggleDarkMode = onToggleDarkMode
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LogoutButton(onLogoutClick = { showLogoutDialog = true })

            Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun ProfileHeaderSection(user: User) {
    val firstLetter = user.displayName.firstOrNull()?.uppercase() ?: "B"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(94.dp)
                .clip(CircleShape)
                .background(BooklyGreenPrimary),
            contentAlignment = Alignment.Center
        ) {
            if (!user.photoUrl.isNullOrBlank()) {
                AsyncImage(
                    model = user.photoUrl,
                    contentDescription = "Avatar của ${user.displayName}",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Text(
                    text = firstLetter,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = user.displayName.ifBlank { "Người dùng Bookly" },
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        if (user.bio.isNotBlank()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "\"${user.bio}\"",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
private fun ReadingStatsSection(
    user: User,
    bookmarkCount: Int,
    currentlyReadingCount: Int,
    finishedReadingCount: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        StatItem(
            count = currentlyReadingCount,
            label = "Đang đọc",
            icon = Icons.Default.AutoStories,
            color = Color(0xFF3B82F6)
        )

        Box(
            modifier = Modifier
                .width(1.dp)
                .height(30.dp)
                .background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))
        )

        StatItem(
            count = finishedReadingCount,
            label = "Đã xong",
            icon = Icons.Default.CheckCircle,
            color = BooklyGreenPrimary
        )

        Box(
            modifier = Modifier
                .width(1.dp)
                .height(30.dp)
                .background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))
        )

        StatItem(
            count = bookmarkCount,
            label = "Yêu thích",
            icon = Icons.Default.Favorite,
            color = Color(0xFFEF4444)
        )
    }
}

@Composable
private fun AccountDetailsSection(user: User) {
    val context = LocalContext.current
    val feedbackEmail = "hongmanhunity@gmail.com"

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Thông tin tài khoản",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        ProfileInfoRow(
            icon = Icons.Default.Email,
            title = "Email",
            value = user.email.ifBlank { "Chưa có email" }
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp),
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
            modifier = Modifier.padding(vertical = 5.dp),
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
        )

        ProfileInfoRow(
            icon = Icons.Default.Info,
            title = "Phiên bản ứng dụng",
            value = "Bookly v1.0.0"
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp),
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
        )

        ProfileInfoRow(
            icon = Icons.Default.Feedback,
            title = "Liên hệ & Góp ý",
            value = feedbackEmail,
            subtitle = "Chạm để gửi phản hồi",
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:$feedbackEmail")
                    putExtra(Intent.EXTRA_SUBJECT, "[Bookly] Góp ý & Phản hồi")
                }
                try {
                    context.startActivity(Intent.createChooser(intent, "Gửi email phản hồi"))
                } catch (_: Exception) {
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
                    clipboard?.setPrimaryClip(ClipData.newPlainText("Email feedback", feedbackEmail))
                    Toast.makeText(context, "Đã sao chép: $feedbackEmail", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

@Composable
private fun ThemeSettingSection(
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Giao diện",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = if (isDarkMode) Icons.Default.DarkMode else Icons.Default.LightMode,
                    contentDescription = null,
                    tint = if (isDarkMode) Color(0xFFF59E0B) else Color(0xFFFBBF24),
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Chế độ hiển thị",
                        fontSize = 13.5.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = if (isDarkMode) "Đang bật giao diện tối" else "Đang bật giao diện sáng",
                        fontSize = 11.5.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Switch(
                checked = isDarkMode,
                onCheckedChange = onToggleDarkMode,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = BooklyGreenPrimary
                )
            )
        }
    }
}

@Composable
private fun LogoutButton(onLogoutClick: () -> Unit) {
    Button(
        onClick = onLogoutClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFEE2E2)
        ),
        elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Logout,
            contentDescription = null,
            tint = Color(0xFFDC2626),
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Đăng xuất tài khoản",
            fontSize = 14.5.sp,
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
                modifier = Modifier.size(17.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = count.toString(),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.5.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProfileInfoRow(
    icon: ImageVector,
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    onClick: (() -> Unit)? = null
) {
    val rowModifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
        .padding(vertical = 2.dp)

    Row(
        modifier = rowModifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BooklyGreenPrimary,
                modifier = Modifier.size(17.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                fontSize = 13.5.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
