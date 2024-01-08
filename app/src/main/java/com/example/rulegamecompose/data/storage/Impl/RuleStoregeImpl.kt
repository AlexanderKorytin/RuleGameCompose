package com.example.rulegamecompose.data.storage.Impl

import android.content.SharedPreferences
import com.example.rulegamecompose.data.storage.RuleStorage

class RuleStorageImpl(private val sharedPreferences: SharedPreferences): RuleStorage{
    override fun getCash(): Float =
        sharedPreferences.getFloat(KEY_CASH, 100f)

    override fun setCash(value: Float) {
        sharedPreferences.edit().putFloat(KEY_CASH, value).apply()
    }

    companion object{
        private const val KEY_CASH = "key cash"
    }
}