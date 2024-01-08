package com.example.rulegamecompose.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rulegamecompose.domain.api.RuleInteractor
import com.example.rulegamecompose.ui.models.ValueList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class RuleViewModel(private val interactor: RuleInteractor) : ViewModel() {

    init {
        interactor.setBankSize(interactor.getBankSize())
    }

    private var bankSize: MutableLiveData<Float> = MutableLiveData(interactor.getBankSize())
    fun getBankSize(): LiveData<Float> = bankSize

    private var gettingNumber: MutableStateFlow<Float> = MutableStateFlow(0f)
    fun getCurrentNumberState() = flow<Float> { emit(gettingNumber.value) }

    private var currentIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    fun getCurrentIndex() = flow<Int> { emit(currentIndex.value) }

    private var amountBet: MutableLiveData<Float> = MutableLiveData(0f)
    fun setAmountBet(value: Float) {
        amountBet.value = value
    }

    fun getAmountBet() = amountBet

    private var setNumberBet: MutableLiveData<MutableSet<Int>> = MutableLiveData(mutableSetOf())
    fun setListNumberBet(value: Set<Int>) {
        setNumberBet.value?.clear()
        setNumberBet.value?.addAll(value)
    }

    fun refreshMoney() {
        interactor.setBankSize(100f)
    }

    fun setResultBank() {
        val bank = interactor.getBankSize()
        bankSize.postValue(bank)
        if ((amountBet.value!! >= bank) == true) {
            amountBet.value = bank
        }

    }

    fun twistRule(currentValue: Float) {
        gettingNumber.value = interactor.getDroppedNumber(currentValue)
        viewModelScope.launch(Dispatchers.Default) {
            getCurrentNumberState().collect {
                currentIndex.value = interactor.getIndex(it)
                val resultNumber = ValueList.list[currentIndex.value].valueNumber
                if (!setNumberBet.value.isNullOrEmpty()) {
                    val betValue: Float = amountBet.value?.toFloat()!! / setNumberBet.value?.size!!

                    if (setNumberBet.value!!.contains(resultNumber) == false) {
                        interactor.setBankSize(interactor.getBankSize() - amountBet.value!!)
                    } else {
                        interactor.setBankSize(
                            (interactor.getBankSize() + betValue * 2) - (amountBet.value!! - betValue)
                        )
                    }
                } else {
                    return@collect
                }
            }
        }
    }
}
