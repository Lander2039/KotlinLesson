package com.example.kotlinlesson.di

import com.example.kotlinlesson.domain.ItemsInteractor
import com.example.kotlinlesson.domain.ItemsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    fun provideItemsInteractor(itemsRepository: ItemsRepository): ItemsInteractor{
        return ItemsInteractor(itemsRepository)
    }
}