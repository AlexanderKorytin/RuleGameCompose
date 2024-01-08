package com.example.rulegamecompose.data.impl

import com.example.rulegamecompose.data.storage.RuleStorage
import com.example.rulegamecompose.domain.api.RuleRepositiry

class RuleRepositoryImpl(private val storage: RuleStorage): RuleRepositiry {
    override fun saveCash(value: Int) {
        storage.setCash(value)
    }

    override fun getCashValue(): Int = storage.getCash()
}