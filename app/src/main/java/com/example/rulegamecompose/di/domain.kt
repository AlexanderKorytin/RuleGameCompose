package com.example.rulegamecompose.di

import com.example.rulegamecompose.domain.impl.RuleInteractorImpl
import org.koin.dsl.module

val domainModule = module {
    factory { RuleInteractorImpl() }
}