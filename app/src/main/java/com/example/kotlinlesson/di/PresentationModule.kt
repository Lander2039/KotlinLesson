package com.example.kotlinlesson.di

import com.example.kotlinlesson.domain.auth.AuthInteractor
import com.example.kotlinlesson.domain.items.ItemsInteractor
import com.example.kotlinlesson.presentation.view.auth.LoginPresenter
import com.example.kotlinlesson.presentation.view.auth.OnBoardingPresenter
import com.example.kotlinlesson.presentation.view.home.DetailsPresenter
import com.example.kotlinlesson.presentation.view.home.ItemsPresenter
import com.example.kotlinlesson.presentation.view.mvp.MainPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {

    @Provides
    fun provideMainPresenter(authInteractor: AuthInteractor): MainPresenter {
        return MainPresenter(authInteractor)
    }

    @Provides
    fun provideLoginPresenter(authInteractor: AuthInteractor): LoginPresenter {
        return LoginPresenter(authInteractor)
    }

    @Provides
    fun provideOnBoardingPresenter(): OnBoardingPresenter {
        return OnBoardingPresenter()
    }

    @Provides
    fun provideItemsPresenter(itemsInteractor: ItemsInteractor): ItemsPresenter {
        return ItemsPresenter(itemsInteractor)
    }

    @Provides
    fun provideDetailsPresenter(authInteractor: AuthInteractor): DetailsPresenter {
        return DetailsPresenter(authInteractor)
    }
}