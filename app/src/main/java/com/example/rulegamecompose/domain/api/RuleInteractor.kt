package com.example.rulegamecompose.domain.api

interface RuleInteractor {
    fun getDroppedNumber(currentValue: Float): Float

    fun getIndex(value: Float): Int

    fun getBankSize(): Float

    fun setBankSize(value: Float)
}