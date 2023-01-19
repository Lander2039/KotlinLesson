package com.example.kotlinlesson.di

import android.content.Context
import com.example.kotlinlesson.data.database.dao.ItemsDAO
import com.example.kotlinlesson.data.database.dao.ItemsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideItemsDao(itemsDatabase: ItemsDatabase): ItemsDAO{
        return itemsDatabase.getItemsDAO()
    }

    @Provides
    fun itemsDatabase(context: Context) :ItemsDatabase{
        return ItemsDatabase.getItemsDatabaseInstance(context)
    }
}