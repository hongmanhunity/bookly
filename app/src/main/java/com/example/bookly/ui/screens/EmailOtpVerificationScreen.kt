package com.example.bookly.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookly.ui.components.common.CanvasTheme
import com.example.bookly.ui.state.AuthUiState
import com.example.bookly.ui.theme.BooklyGreenPrimary
import com.example.bookly.ui.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun EmailOtpVerificationScreen(
    email: String,
    initialOtpCode: String,
    onVerifiedSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    var otpValue by rememberSaveable { mutableStateOf("") }
    var countdownSeconds by remember { mutableIntStateOf(60) }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(uiState) {
        when (uiState) {
            is AuthUiState.OtpSent -> {
                countdownSeconds = 60
                Toast.makeText(context, "✉️ Đã gửi lại mã OTP 6 số mới!", Toast.LENGTH_SHORT).show()
            }
            is AuthUiState.OtpVerified -> {
                Toast.makeText(context, "🎉 Nhập mã OTP thành công!", Toast.LENGTH_LONG).show()
                onVerifiedSuccess()
                viewModel.resetUiState()
            }
            else -> {}
        }
    }

    // Đếm ngược 60s
    LaunchedEffect(countdownSeconds) {
        if (countdownSeconds > 0) {
            delay(1000L)
            countdownSeconds--
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CanvasTheme(modifier = Modifier.fillMaxSize())

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Icon Hero
            Surface(
                shape = CircleShape,
                color = BooklyGreenPrimary.copy(alpha = 0.12f),
                modifier = Modifier.size(88.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = "OTP Security",
                        tint = BooklyGreenPrimary,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Xác Thực Mã OTP 6 Số",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Vui lòng nhập mã OTP gồm 6 chữ số đã được gửi tới địa chỉ email của bạn.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF64748B),
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            // 6 Ô vuông nhập OTP
            OtpSixBoxesView(
                otpText = otpValue,
                onOtpTextChange = {
                    if (it.length <= 6) {
                        otpValue = it
                        if (it.length == 6) {
                            viewModel.verifyOtp(email, it)
                        }
                    }
                },
                focusRequester = focusRequester
            )

            if (uiState is AuthUiState.Error) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = (uiState as AuthUiState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Nút Xác Thực
            Button(
                onClick = { viewModel.verifyOtp(email, otpValue) },
                enabled = otpValue.length == 6 && uiState !is AuthUiState.Loading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BooklyGreenPrimary,
                    contentColor = Color.White
                )
            ) {
                if (uiState is AuthUiState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = "Xác Thực Mã OTP",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Nút Gửi Lại Mã OTP
            OutlinedButton(
                onClick = {
                    if (countdownSeconds == 0) {
                        viewModel.resendOtp(email)
                    }
                },
                enabled = countdownSeconds == 0 && uiState !is AuthUiState.Loading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = if (countdownSeconds > 0) "Gửi lại mã OTP sau (${countdownSeconds}s)" else "Gửi Lại Mã OTP",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (countdownSeconds == 0) BooklyGreenPrimary else Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(
                onClick = {
                    viewModel.resetUiState()
                    onNavigateToLogin()
                }
            ) {
                Text(
                    text = "Quay lại Đăng nhập",
                    color = Color(0xFF64748B),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun OtpSixBoxesView(
    otpText: String,
    onOtpTextChange: (String) -> Unit,
    focusRequester: FocusRequester
) {
    BasicTextField(
        value = otpText,
        onValueChange = onOtpTextChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.focusRequester(focusRequester),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(6) { index ->
                    val digit = otpText.getOrNull(index)?.toString() ?: ""
                    val isFocused = index == otpText.length

                    Box(
                        modifier = Modifier
                            .size(46.dp, 54.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF8FAFC))
                            .border(
                                width = if (isFocused) 2.dp else 1.dp,
                                color = if (isFocused) BooklyGreenPrimary else Color(0xFFE2E8F0),
                                shape = RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = digit,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    )
}
