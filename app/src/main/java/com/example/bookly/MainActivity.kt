package com.example.bookly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookly.data.seeder.FirestoreSeeder
import com.example.bookly.ui.screens.BookDetailScreen
import com.example.bookly.ui.screens.BookScreen
import com.example.bookly.ui.screens.EmailOtpVerificationScreen
import com.example.bookly.ui.screens.HomeScreen
import com.example.bookly.ui.screens.LoginScreen
import com.example.bookly.ui.screens.ProfileScreen
import com.example.bookly.ui.screens.ReaderScreen
import com.example.bookly.ui.screens.RegisterScreen
import com.example.bookly.ui.theme.BooklyGreenPrimary
import com.example.bookly.ui.theme.BooklyTheme

enum class Screen {
    LOGIN,
    REGISTER,
    EMAIL_OTP,
    HOME,
    BOOK_LIST,
    BOOK_DETAIL,
    READER,
    PROFILE
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BooklyTheme {
                var currentScreen by remember { mutableStateOf(Screen.LOGIN) }
                var previousScreen by remember { mutableStateOf(Screen.HOME) }
                var selectedBookId by remember { mutableStateOf<String?>(null) }
                var selectedChapterNum by remember { mutableStateOf(1) }
                var registeredEmail by remember { mutableStateOf("") }
                var currentOtpCode by remember { mutableStateOf("") }

                // Chỉ gieo dữ liệu nếu chưa có trên Firestore (forceReSeed = false)
                LaunchedEffect(Unit) {
                    FirestoreSeeder.seedBooks(forceReSeed = false)
                }

                val showBottomBar = currentScreen in listOf(Screen.HOME, Screen.BOOK_LIST, Screen.PROFILE)
                val showTopBar = currentScreen in listOf(Screen.HOME, Screen.BOOK_LIST)

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        if (showTopBar) {
                            BooklyTopBar(title = "bookly")
                        }
                    },
                    bottomBar = {
                        if (showBottomBar) {
                            BooklyBottomNav(
                                currentScreen = currentScreen,
                                onScreenSelected = { screen ->
                                    selectedBookId = null
                                    currentScreen = screen
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    val modifier = Modifier.padding(innerPadding)

                    when (currentScreen) {
                        Screen.LOGIN -> {
                            LoginScreen(
                                modifier = modifier,
                                onNavigateToRegister = { currentScreen = Screen.REGISTER },
                                onLoginSuccess = { currentScreen = Screen.HOME }
                            )
                        }

                        Screen.REGISTER -> {
                            RegisterScreen(
                                modifier = modifier,
                                onNavigateToLogin = { currentScreen = Screen.LOGIN },
                                onRegisterSuccessWithOtp = { email, otp ->
                                    registeredEmail = email
                                    currentOtpCode = otp
                                    currentScreen = Screen.EMAIL_OTP
                                }
                            )
                        }

                        Screen.EMAIL_OTP -> {
                            EmailOtpVerificationScreen(
                                email = registeredEmail,
                                initialOtpCode = currentOtpCode,
                                onVerifiedSuccess = { currentScreen = Screen.LOGIN },
                                onNavigateToLogin = { currentScreen = Screen.LOGIN },
                                modifier = modifier
                            )
                        }

                        Screen.HOME -> {
                            HomeScreen(
                                modifier = modifier,
                                onBookClick = { id ->
                                    selectedBookId = id
                                    previousScreen = Screen.HOME
                                    currentScreen = Screen.BOOK_DETAIL
                                }
                            )
                        }

                        Screen.BOOK_LIST -> {
                            BookScreen(
                                modifier = modifier,
                                onBookClick = { id ->
                                    selectedBookId = id
                                    previousScreen = Screen.BOOK_LIST
                                    currentScreen = Screen.BOOK_DETAIL
                                }
                            )
                        }

                        Screen.BOOK_DETAIL -> {
                            if (selectedBookId != null) {
                                BookDetailScreen(
                                    bookId = selectedBookId!!,
                                    onBackClick = {
                                        currentScreen = previousScreen
                                    },
                                    onChapterClick = { chapterNum ->
                                        selectedChapterNum = chapterNum
                                        currentScreen = Screen.READER
                                    }
                                )
                            }
                        }

                        Screen.READER -> {
                            if (selectedBookId != null) {
                                ReaderScreen(
                                    bookId = selectedBookId!!,
                                    initialChapterNumber = selectedChapterNum,
                                    onBackClick = {
                                        currentScreen = Screen.BOOK_DETAIL
                                    }
                                )
                            }
                        }

                        Screen.PROFILE -> {
                            ProfileScreen(
                                modifier = modifier,
                                onLogoutClick = {
                                    currentScreen = Screen.LOGIN
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooklyTopBar(title: String = "bookly") {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                color = BooklyGreenPrimary,
                letterSpacing = (-0.5).sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

@Composable
fun BooklyBottomNav(
    currentScreen: Screen,
    onScreenSelected: (Screen) -> Unit
) {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            selected = currentScreen == Screen.HOME,
            onClick = { onScreenSelected(Screen.HOME) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Trang chủ") },
            label = { Text("Trang chủ") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = BooklyGreenPrimary,
                selectedTextColor = BooklyGreenPrimary,
                indicatorColor = Color(0x204EBA87)
            )
        )
        NavigationBarItem(
            selected = currentScreen == Screen.BOOK_LIST,
            onClick = { onScreenSelected(Screen.BOOK_LIST) },
            icon = { Icon(Icons.Default.Book, contentDescription = "Tủ sách") },
            label = { Text("Tủ sách") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = BooklyGreenPrimary,
                selectedTextColor = BooklyGreenPrimary,
                indicatorColor = Color(0x204EBA87)
            )
        )
        NavigationBarItem(
            selected = currentScreen == Screen.PROFILE,
            onClick = { onScreenSelected(Screen.PROFILE) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Cá nhân") },
            label = { Text("Cá nhân") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = BooklyGreenPrimary,
                selectedTextColor = BooklyGreenPrimary,
                indicatorColor = Color(0x204EBA87)
            )
        )
    }
}