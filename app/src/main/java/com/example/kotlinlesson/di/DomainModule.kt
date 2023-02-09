package com.example.kotlinlesson.di

import com.example.kotlinlesson.domain.auth.AuthInteractor
import com.example.kotlinlesson.domain.auth.AuthRepository
import com.example.kotlinlesson.domain.items.ItemsInteractor
import com.example.kotlinlesson.domain.items.ItemsRepository
import dagger.Module
import dagger.Provides


@Module

class DomainModule {


    @Provides
    fun provideItemsInteractor(itemsRepository: ItemsRepository): ItemsInteractor {
        return ItemsInteractor(itemsRepository)
    }

    @Provides
    fun provideAuthInteractor(authRepository: AuthRepository): AuthInteractor {
        return AuthInteractor(authRepository)
    }
}