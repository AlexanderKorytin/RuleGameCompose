package com.example.rulegamecompose.di

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.rulegamecompose.data.storage.Impl.RuleStorageImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val APP_KEY = "app key"
val dataModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            APP_KEY,
            AppCompatActivity.MODE_PRIVATE
        )
    }

    single { RuleStorageImpl(sharedPreferences = get()) }
}