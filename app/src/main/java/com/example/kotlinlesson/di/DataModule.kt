package com.example.kotlinlesson.di

import com.example.kotlinlesson.data.auth.AuthRepositoryImpl
import com.example.kotlinlesson.data.items.ItemsRepositoryImpl
import com.example.kotlinlesson.domain.auth.AuthRepository
import com.example.kotlinlesson.domain.items.ItemsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindItemsRepository(
        itemsRepositoryImpl: ItemsRepositoryImpl
    ): ItemsRepository

    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}

