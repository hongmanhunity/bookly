package com.example.bookly

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.bookly.data.local.ThemeManager
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookly.ui.navigation.Screen
import com.example.bookly.ui.screens.BookDetailScreen
import com.example.bookly.ui.screens.BookmarkScreen
import com.example.bookly.ui.screens.BookScreen
import com.example.bookly.ui.screens.EmailOtpVerificationScreen
import com.example.bookly.ui.screens.HomeScreen
import com.example.bookly.ui.screens.LoginScreen
import com.example.bookly.ui.screens.ProfileScreen
import com.example.bookly.ui.screens.ReaderScreen
import com.example.bookly.ui.screens.RegisterScreen
import com.example.bookly.ui.theme.BooklyGreenPrimary
import com.example.bookly.ui.theme.BooklyTheme
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val themeManager: ThemeManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkMode by themeManager.isDarkMode.collectAsState()
            BooklyTheme(darkTheme = isDarkMode) {
                val navController = rememberNavController()

                val isUserLoggedIn = remember { FirebaseAuth.getInstance().currentUser != null }
                val startDestination = if (isUserLoggedIn) Screen.Home.route else Screen.Login.route

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val showBottomBar = currentRoute in listOf(
                    Screen.Home.route,
                    Screen.BookList.route,
                    Screen.Bookmarks.route,
                    Screen.Profile.route
                )
                val showTopBar = currentRoute in listOf(
                    Screen.Home.route,
                    Screen.BookList.route,
                    Screen.Bookmarks.route
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        if (showTopBar) {
                            BooklyTopBar(
                                title = "bookly"
                            )
                        }
                    },
                    bottomBar = {
                        if (showBottomBar) {
                            BooklyBottomNav(
                                currentRoute = currentRoute,
                                onNavigateToRoute = { targetRoute ->
                                    if (currentRoute != targetRoute) {
                                        navController.navigate(targetRoute) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = startDestination,
                        modifier = Modifier.padding(
                            top = innerPadding.calculateTopPadding(),
                            bottom = if (showBottomBar) innerPadding.calculateBottomPadding() else 0.dp
                        )
                    ) {
                        composable(Screen.Login.route) {
                            LoginScreen(
                                onNavigateToRegister = {
                                    navController.navigate(Screen.Register.route)
                                },
                                onLoginSuccess = {
                                    navController.navigate(Screen.Home.route) {
                                        popUpTo(Screen.Login.route) { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable(Screen.Register.route) {
                            RegisterScreen(
                                onNavigateToLogin = {
                                    navController.popBackStack()
                                },
                                onRegisterSuccess = { email ->
                                    navController.navigate(Screen.EmailOtp.createRoute(email))
                                },
                                onGoogleSuccess = {
                                    navController.navigate(Screen.Home.route) {
                                        popUpTo(Screen.Login.route) { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable(
                            route = Screen.EmailOtp.route,
                            arguments = listOf(
                                navArgument("email") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val rawEmail = backStackEntry.arguments?.getString("email") ?: ""
                            val email = Uri.decode(rawEmail)

                            EmailOtpVerificationScreen(
                                email = email,
                                onVerifiedSuccess = {
                                    navController.navigate(Screen.Login.route) {
                                        popUpTo(Screen.Register.route) { inclusive = true }
                                    }
                                },
                                onNavigateToLogin = {
                                    navController.navigate(Screen.Login.route) {
                                        popUpTo(Screen.Register.route) { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable(Screen.Home.route) {
                            HomeScreen(
                                onBookClick = { bookId ->
                                    navController.navigate(Screen.BookDetail.createRoute(bookId))
                                }
                            )
                        }

                        composable(Screen.BookList.route) {
                            BookScreen(
                                onBookClick = { bookId ->
                                    navController.navigate(Screen.BookDetail.createRoute(bookId))
                                }
                            )
                        }

                        composable(Screen.Profile.route) {
                            ProfileScreen(
                                onLogoutClick = {
                                    navController.navigate(Screen.Login.route) {
                                        popUpTo(0) { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable(
                            route = Screen.BookDetail.route,
                            arguments = listOf(
                                navArgument("bookId") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
                            BookDetailScreen(
                                bookId = bookId,
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onChapterClick = { chapterNum ->
                                    navController.navigate(Screen.Reader.createRoute(bookId, chapterNum))
                                }
                            )
                        }

                        composable(
                            route = Screen.Reader.route,
                            arguments = listOf(
                                navArgument("bookId") { type = NavType.StringType },
                                navArgument("chapterNumber") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
                            val chapterNum = backStackEntry.arguments?.getInt("chapterNumber") ?: 1

                            ReaderScreen(
                                bookId = bookId,
                                initialChapterNumber = chapterNum,
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(Screen.Bookmarks.route) {
                            BookmarkScreen(
                                onBackClick = null,
                                onBookClick = { bookId ->
                                    navController.navigate(Screen.BookDetail.createRoute(bookId))
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
fun BooklyTopBar(
    title: String = "bookly"
) {
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
            containerColor = androidx.compose.material3.MaterialTheme.colorScheme.background,
            scrolledContainerColor = androidx.compose.material3.MaterialTheme.colorScheme.background
        )
    )
}

@Composable
fun BooklyBottomNav(
    currentRoute: String?,
    onNavigateToRoute: (String) -> Unit
) {
    NavigationBar(
        containerColor = androidx.compose.material3.MaterialTheme.colorScheme.background,
        tonalElevation = 0.dp
    ) {
        NavigationBarItem(
            selected = currentRoute == Screen.Home.route,
            onClick = { onNavigateToRoute(Screen.Home.route) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Trang chủ") },
            label = { Text("Trang chủ") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = BooklyGreenPrimary,
                selectedTextColor = BooklyGreenPrimary,
                indicatorColor = Color(0x204EBA87)
            )
        )
        NavigationBarItem(
            selected = currentRoute == Screen.BookList.route,
            onClick = { onNavigateToRoute(Screen.BookList.route) },
            icon = { Icon(Icons.Default.Book, contentDescription = "Tủ sách") },
            label = { Text("Tủ sách") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = BooklyGreenPrimary,
                selectedTextColor = BooklyGreenPrimary,
                indicatorColor = Color(0x204EBA87)
            )
        )
        NavigationBarItem(
            selected = currentRoute == Screen.Bookmarks.route,
            onClick = { onNavigateToRoute(Screen.Bookmarks.route) },
            icon = { Icon(Icons.Default.Bookmark, contentDescription = "Yêu thích") },
            label = { Text("Yêu thích") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = BooklyGreenPrimary,
                selectedTextColor = BooklyGreenPrimary,
                indicatorColor = Color(0x204EBA87)
            )
        )
        NavigationBarItem(
            selected = currentRoute == Screen.Profile.route,
            onClick = { onNavigateToRoute(Screen.Profile.route) },
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
