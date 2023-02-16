package com.example.kotlinlesson.di

import android.content.Context
import com.example.kotlinlesson.data.auth.AuthRepositoryImpl
import com.example.kotlinlesson.data.items.ItemsRepositoryImpl
import com.example.kotlinlesson.data.service.ApiService
import com.example.kotlinlesson.data.service.ApiServiceSecond
import com.example.kotlinlesson.data.sharedPreferences.SharedPreferencesHelper
import com.example.kotlinlesson.domain.auth.AuthRepository
import com.example.kotlinlesson.domain.items.ItemsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module

abstract class DataModule {

    @Binds
    abstract fun bindItemsRepository(
        itemsRepositoryImpl: ItemsRepositoryImpl
    ): ItemsRepository

    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    companion object {
        private const val BASE_URL = "https://api.jsonserve.com"
        private const val SP_KEY = "SP_KEY"
        private const val BASE_URL_SECOND = "https://jsonplaceholder.typicode.com"

        @Provides
        fun provideSharedPreferences(
            context: Context
        ): SharedPreferencesHelper {
            return SharedPreferencesHelper(
                context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE)
            )
        }

        @Named("FIRST")
        @Provides
        fun provideApiService(@Named("FIRST") retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }

        @Named("FIRST")
        @Provides
        fun provideRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        @Named("SECOND")
        @Provides
        fun provideApiServiceSecond(@Named("SECOND") retrofit: Retrofit): ApiServiceSecond {
            return retrofit.create(ApiServiceSecond::class.java)
        }

        @Named("SECOND")
        @Provides
        fun provideRetrofitInstanceSecond(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL_SECOND)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}