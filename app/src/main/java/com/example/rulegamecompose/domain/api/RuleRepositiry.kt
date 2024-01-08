package com.example.rulegamecompose.domain.api

interface RuleRepositiry {
    fun saveCash(value: Float)
    fun getCashValue(): Float
}