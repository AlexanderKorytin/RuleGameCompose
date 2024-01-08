package com.example.rulegamecompose.di

import com.example.rulegamecompose.ui.viewmodel.RuleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<RuleViewModel> {
        RuleViewModel(interactor = get())
    }
}