package com.example.kotlinlesson.di

import android.content.Context
import com.example.kotlinlesson.data.database.dao.ItemsDAO
import com.example.kotlinlesson.data.database.dao.ItemsDatabase
import dagger.Module
import dagger.Provides

@Module

class DatabaseModule {

    @Provides
    fun provideItemsDao(itemsDatabase: ItemsDatabase): ItemsDAO {
        return itemsDatabase.getItemsDAO()
    }

    @Provides
    fun itemsDatabase(context: Context): ItemsDatabase {
        return ItemsDatabase.getItemsDatabaseInstance(context)
    }
}