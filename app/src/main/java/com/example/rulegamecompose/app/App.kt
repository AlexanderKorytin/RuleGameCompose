package com.example.rulegamecompose.app

import android.app.Application
import com.example.rulegamecompose.di.dataModule
import com.example.rulegamecompose.di.domainModule
import com.example.rulegamecompose.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                dataModule,
                domainModule,
                viewModelModule,
            )
        }
    }
}