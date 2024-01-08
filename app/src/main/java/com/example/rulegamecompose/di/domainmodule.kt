package com.example.rulegamecompose.di

import com.example.rulegamecompose.data.impl.RuleRepositoryImpl
import com.example.rulegamecompose.domain.api.RuleInteractor
import com.example.rulegamecompose.domain.api.RuleRepositiry
import com.example.rulegamecompose.domain.impl.RuleInteractorImpl
import org.koin.dsl.module

val domainModule = module {
    single<RuleRepositiry> { RuleRepositoryImpl(storage = get()) }
    factory<RuleInteractor> { RuleInteractorImpl(repositiry = get()) }
}