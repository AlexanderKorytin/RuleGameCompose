package com.example.rulegamecompose.domain.impl

import com.example.rulegamecompose.domain.api.RuleInteractor
import com.example.rulegamecompose.domain.api.RuleRepositiry
import com.example.rulegamecompose.ui.models.ValueList
import kotlin.math.roundToInt

class RuleInteractorImpl(private val repositiry: RuleRepositiry) : RuleInteractor {
    override fun getDroppedNumber(currentValue: Float): Float =
        (720..3600).random().toFloat() + currentValue

    override fun getIndex(currentValue: Float): Int {
        val index = (360f - (currentValue % 360)) / (360f / ValueList.list.size)
        return index.roundToInt()
    }

    override fun getBankSize(): Float = repositiry.getCashValue()


    override fun setBankSize(value: Float) {
        repositiry.saveCash(value)
    }
}
