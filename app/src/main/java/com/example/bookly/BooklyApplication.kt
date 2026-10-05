package com.example.bookly

import android.app.Application
import com.example.bookly.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BooklyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BooklyApplication)
            modules(appModule)
        }
    }
}
