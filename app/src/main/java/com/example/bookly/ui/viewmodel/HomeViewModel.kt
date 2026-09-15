package com.example.bookly.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.data.repository.AuthRepositoryImpl
import com.example.bookly.data.repository.BookRepositoryImpl
import com.example.bookly.domain.repository.AuthRepository
import com.example.bookly.domain.repository.BookRepository
import com.example.bookly.ui.state.HomeUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val bookRepository: BookRepository ,
    private val authRepository: AuthRepository
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = combine(
        bookRepository.getFeaturedBooks(),
        bookRepository.getTrendingBooks(),
        bookRepository.getNewReleases(),
        authRepository.getCurrentUser()
    ) { featured, trending, newReleases, user ->
        val name = user?.displayName?.ifBlank { "Độc Giả Bookly" } ?: "Độc Giả Bookly"
        HomeUiState.Success(
            featuredBooks = featured,
            trendingBooks = trending,
            newReleases = newReleases,
            userDisplayName = name
        ) as HomeUiState
    }.catch { e ->
        emit(HomeUiState.Error(e.message ?: "Đã xảy ra lỗi khi tải dữ liệu trang chủ"))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = HomeUiState.Loading
    )
}
