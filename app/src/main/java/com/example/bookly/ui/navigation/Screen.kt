package com.example.bookly.ui.navigation

import android.net.Uri

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Register : Screen("register")

    data object EmailOtp : Screen("email_otp/{email}") {
        fun createRoute(email: String): String {
            val safeEmail = Uri.encode(email.trim())
            return "email_otp/$safeEmail"
        }
    }

    data object Home : Screen("home")
    data object BookList : Screen("book_list")
    data object Profile : Screen("profile")

    data object BookDetail : Screen("book_detail/{bookId}") {
        fun createRoute(bookId: String): String = "book_detail/$bookId"
    }

    data object Reader : Screen("reader/{bookId}/{chapterNumber}") {
        fun createRoute(bookId: String, chapterNumber: Int): String = "reader/$bookId/$chapterNumber"
    }
    data object Bookmarks: Screen("bookmarks")
}
