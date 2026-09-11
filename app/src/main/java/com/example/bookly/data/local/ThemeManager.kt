package com.example.bookly.data.local

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ThemeManager(context: Context) {
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val _isDarkMode = MutableStateFlow(prefs.getBoolean(KEY_IS_DARK_MODE, false))
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    fun setDarkMode(enabled: Boolean) {
        _isDarkMode.value = enabled
        prefs.edit().putBoolean(KEY_IS_DARK_MODE, enabled).apply()
    }

    companion object {
        private const val PREFS_NAME = "bookly_theme_prefs"
        private const val KEY_IS_DARK_MODE = "is_dark_mode"
    }
}
