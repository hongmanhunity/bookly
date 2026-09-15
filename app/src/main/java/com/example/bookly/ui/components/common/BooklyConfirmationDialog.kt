package com.example.bookly.ui.components.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.bookly.ui.theme.BooklyGreenLight
import com.example.bookly.ui.theme.BooklyGreenPrimary

@Composable
fun BooklyConfirmationDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    confirmText: String = "Xác nhận",
    dismissText: String = "Hủy",
    icon: ImageVector? = null,
    isDestructive: Boolean = true
) {
    val isDark = isSystemInDarkTheme()

    // 🎨 Bảng màu Pastel chuẩn DNA của Bookly
    val confirmBtnContainerColor = when {
        isDestructive && !isDark -> Color(0xFFFEE2E2) // Đỏ pastel dịu mát của Bookly
        isDestructive && isDark -> Color(0xFF3F1D1D)  // Đỏ trầm trong Dark Mode
        !isDestructive && !isDark -> BooklyGreenLight // Xanh nhạt Bookly
        else -> BooklyGreenPrimary.copy(alpha = 0.2f)
    }

    val confirmBtnContentColor = when {
        isDestructive && !isDark -> Color(0xFFDC2626)
        isDestructive && isDark -> Color(0xFFF87171)
        else -> BooklyGreenPrimary
    }

    val iconContainerColor = when {
        isDestructive && !isDark -> Color(0xFFFEE2E2)
        isDestructive && isDark -> Color(0xFF3F1D1D)
        else -> BooklyGreenPrimary.copy(alpha = 0.15f)
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Surface(
            shape = RoundedCornerShape(26.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 2.dp,
            shadowElevation = 10.dp,
            modifier = modifier
                .fillMaxWidth()
                .widthIn(max = 320.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 1. Header Banner phong cách Bookly (Lấy cảm hứng từ Email Banner)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(78.dp)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFF3EAF7C), BooklyGreenPrimary, Color(0xFF389266))
                            )
                        )
                ) {
                    // Họa tiết vòng tròn nghệ thuật mờ
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawCircle(
                            color = Color.White.copy(alpha = 0.12f),
                            radius = 45.dp.toPx(),
                            center = Offset(size.width * 0.12f, size.height * 0.25f)
                        )
                        drawCircle(
                            color = Color.White.copy(alpha = 0.08f),
                            radius = 22.dp.toPx(),
                            center = Offset(size.width * 0.88f, size.height * 0.7f)
                        )
                    }

                    // Tên thương hiệu bookly được canh khoảng cách tự nhiên, không bị sát mép trên
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 18.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Text(
                            text = "bookly",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = (-0.5).sp
                        )
                    }
                }

                // 2. Huy hiệu Icon trung tâm nổi giữa Header và Nội dung
                if (icon != null) {
                    Box(
                        modifier = Modifier
                            .offset(y = (-24).dp)
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(iconContainerColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = confirmBtnContentColor,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }
                } else {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // 3. Nội dung văn bản với font & cỡ chữ tự nhiên
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 22.dp)
                        .offset(y = if (icon != null) (-12).dp else 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        lineHeight = 21.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // 4. Hàng Nút Bấm Viên Thuốc (Pill Shape Buttons)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = onDismiss,
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                        ) {
                            Text(
                                text = dismissText,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                        }

                        Button(
                            onClick = {
                                onDismiss()
                                onConfirm()
                            },
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = confirmBtnContainerColor,
                                contentColor = confirmBtnContentColor
                            ),
                            elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                        ) {
                            Text(
                                text = confirmText,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}
