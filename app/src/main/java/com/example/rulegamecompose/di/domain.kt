package com.example.rulegamecompose.di

import com.example.rulegamecompose.data.impl.RuleRepositoryImpl
import com.example.rulegamecompose.domain.impl.RuleInteractorImpl
import org.koin.dsl.module

val domainModule = module {
    single { RuleRepositoryImpl(storage = get()) }
    factory { RuleInteractorImpl() }
}