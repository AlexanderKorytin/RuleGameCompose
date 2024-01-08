package com.example.rulegamecompose.domain.impl

import com.example.rulegamecompose.domain.api.RuleInteractor
import com.example.rulegamecompose.ui.models.ValueList
import kotlin.math.roundToInt

class RuleInteractorImpl : RuleInteractor {
    override fun getDroppedNumber(currentValue: Float): Float =
        (720..3600).random().toFloat() + currentValue

    override fun getIndex(currentValue: Float): Int {
        val index = (360f - (currentValue % 360)) / (360f / ValueList.list.size)
        return index.roundToInt()
    }
}
