package com.example.rulegamecompose.domain.api

interface RuleRepositiry {
    fun saveCash(value: Int)
    fun getCashValue(): Int
}