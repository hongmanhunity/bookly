package com.example.bookly.di

import androidx.room.Room
import com.example.bookly.data.local.BooklyDatabase
import com.example.bookly.data.local.ThemeManager
import com.example.bookly.data.repository.AuthRepositoryImpl
import com.example.bookly.data.repository.BookRepositoryImpl
import com.example.bookly.data.repository.CommentRepositoryImpl
import com.example.bookly.domain.repository.AuthRepository
import com.example.bookly.domain.repository.BookRepository
import com.example.bookly.domain.repository.CommentRepository
import com.example.bookly.ui.viewmodel.AuthViewModel
import com.example.bookly.ui.viewmodel.BookDetailViewModel
import com.example.bookly.ui.viewmodel.BookViewModel
import com.example.bookly.ui.viewmodel.BookmarkViewModel
import com.example.bookly.ui.viewmodel.HomeViewModel
import com.example.bookly.ui.viewmodel.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.example.bookly.ui.viewmodel.CommentViewModel

val appModule = module {
    //Khoi tao Firebase
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single {
        Room.databaseBuilder(
            androidContext(),
            BooklyDatabase::class.java,
            "bookly_database.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<BooklyDatabase>().bookDao() }
    single<AuthRepository> { AuthRepositoryImpl(auth = get(), firestore = get()) }
    single<BookRepository> { BookRepositoryImpl(firestore = get()) }
    single<CommentRepository> { CommentRepositoryImpl(firestore = get(), auth = get()) }
    single { ThemeManager(androidContext()) }
    //Khoi tao ViewModels
    viewModel { AuthViewModel(authRepository = get()) }
    viewModel { BookViewModel(repository = get()) }
    viewModel { BookDetailViewModel(repository = get(), bookDao = get()) }
    viewModel { HomeViewModel(bookRepository = get(), authRepository = get()) }
    viewModel { ProfileViewModel(authRepository = get(), bookDao = get(), themeManager = get()) }
    viewModel { BookmarkViewModel(bookDao = get()) }
    viewModel { CommentViewModel(commentRepository = get(), auth = get()) }
}