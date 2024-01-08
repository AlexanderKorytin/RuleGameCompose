package com.example.rulegamecompose.data.storage

interface RuleStorage {
    fun getCash(): Int

    fun setCash(value: Int)
}