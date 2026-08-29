package com.example.bookly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.bookly.ui.auth.LoginScreen
import com.example.bookly.ui.auth.RegisterScreen
import com.example.bookly.ui.book.BookDetailScreen
import com.example.bookly.ui.book.BookScreen
import com.example.bookly.ui.theme.BooklyTheme

enum class Screen {
    LOGIN,
    REGISTER,
    BOOK_LIST,
    BOOK_DETAIL
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BooklyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var currentScreen by remember { mutableStateOf(Screen.LOGIN) }
                    var selectedBookId by remember { mutableStateOf<String?>(null) }

                    val modifier = Modifier.padding(innerPadding)

                    when (currentScreen) {
                        Screen.LOGIN -> {
                            LoginScreen(
                                modifier = modifier,
                                onNavigateToRegister = { currentScreen = Screen.REGISTER },
                                onLoginSuccess = { currentScreen = Screen.BOOK_LIST }
                            )
                        }

                        Screen.REGISTER -> {
                            RegisterScreen(
                                modifier = modifier,
                                onNavigateToLogin = { currentScreen = Screen.LOGIN },
                                onRegisterSuccess = { currentScreen = Screen.LOGIN },
                            )
                        }

                        Screen.BOOK_LIST -> {
                            if (selectedBookId == null) {
                                BookScreen(
                                    modifier = modifier,
                                    onBookClick = { id -> selectedBookId = id }
                                )
                            } else {
                                BookDetailScreen(
                                    bookId = selectedBookId!!,
                                    onBackClick = { selectedBookId = null }
                                )
                            }
                        }

                        Screen.BOOK_DETAIL -> {
                            if (selectedBookId != null) {
                                BookDetailScreen(
                                    bookId = selectedBookId!!,
                                    onBackClick = { selectedBookId = null }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}