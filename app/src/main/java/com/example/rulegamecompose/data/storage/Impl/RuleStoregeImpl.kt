package com.example.rulegamecompose.data.storage.Impl

import android.content.SharedPreferences
import com.example.rulegamecompose.data.storage.RuleStorage

class RuleStorageImpl(private val sharedPreferences: SharedPreferences): RuleStorage{
    override fun getCash(): Int =
        sharedPreferences.getInt(KEY_CASH, 100)

    override fun setCash(value: Int) {
        sharedPreferences.edit().putInt(KEY_CASH, value)
    }

    companion object{
        private const val KEY_CASH = "key cash"
    }
}