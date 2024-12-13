package com.hussein.madartask

import android.app.Application
import com.hussein.madartask.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger() // Optional: Log Koin events
            androidContext(this@MyApp)
            modules(appModule) // Add the database module
            // ... other modules
        }
    }
}