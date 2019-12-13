package com.example.marvelapi

import android.app.Application
import com.example.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@MarvelApp)
            // declare modules
            modules(
                listOf(
                    presentationModule
                )
            )
        }
    }

}