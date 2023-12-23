package com.example.suitmedia_tes

import android.app.Application
import com.example.suitmedia_tes.di.KoinModule.dataModule
import com.example.suitmedia_tes.di.KoinModule.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(
                listOf(
                    dataModule,
                    uiModule
                )
            )
        }
    }
}