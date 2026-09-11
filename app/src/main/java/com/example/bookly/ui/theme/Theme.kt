package com.example.bookly.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = BooklyGreenPrimary,
    secondary = BooklyGreenLight,
    background = Color(0xFF101010),
    surface = Color(0xFF181818),
    onPrimary = Color.White,
    onBackground = Color(0xFFF0F0F0),
    onSurface = Color(0xFFF0F0F0),
    surfaceVariant = Color(0xFF242424),
    onSurfaceVariant = Color(0xFFB0B0B0)
)

private val LightColorScheme = lightColorScheme(
    primary = BooklyGreenPrimary,
    secondary = BooklyGreenLight,
    background = BooklyBackground,
    surface = BooklyBackground,
    onPrimary = Color.White,
    onBackground = BooklyTextPrimary,
    onSurface = BooklyTextPrimary
)

@Composable
fun BooklyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                val insetsController = WindowCompat.getInsetsController(window, view)
                insetsController.isAppearanceLightStatusBars = !darkTheme
                insetsController.isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}