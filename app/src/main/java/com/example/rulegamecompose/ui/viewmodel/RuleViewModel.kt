package com.example.rulegamecompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rulegamecompose.domain.impl.RuleInteractorImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class RuleViewModel(private val interactor: RuleInteractorImpl) : ViewModel() {
    private var gettingNumber: MutableStateFlow<Float> = MutableStateFlow(0f)
    fun getCurrentNumberState() = flow<Float> { emit(gettingNumber.value) }

    private var curerentIndex: MutableStateFlow<Int> = MutableStateFlow(0)

    fun getCurrentIndex() = flow<Int> { emit(curerentIndex.value) }

    fun twistRule(currentValue: Float) {
        gettingNumber.value = interactor.getDroppedNumber(currentValue)
        viewModelScope.launch(Dispatchers.Default){
            getCurrentNumberState().collect{
                curerentIndex.value = interactor.getIndex(it)
            }
        }
    }

}