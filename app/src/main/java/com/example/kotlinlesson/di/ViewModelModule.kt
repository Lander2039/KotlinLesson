package com.example.kotlinlesson.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinlesson.di.factory.ViewModelFactory
import com.example.kotlinlesson.di.factory.ViewModelKey
import com.example.kotlinlesson.presentation.view.ItemsViewModel
import com.example.kotlinlesson.presentation.view.MainViewModel
import com.example.kotlinlesson.presentation.view.auth.LoginViewModel
import com.example.kotlinlesson.presentation.view.auth.OnBoardingViewModel
import com.example.kotlinlesson.presentation.view.home.HomeViewModel
import com.example.kotlinlesson.presentation.view.home.items.DetailsVIewModel
import com.example.kotlinlesson.presentation.view.home.items.FavoritesViewModel
import com.example.kotlinlesson.presentation.view.home.items.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel (viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindFavoritesViewModel (viewModel: FavoritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsVIewModel::class)
    abstract fun bindDetailsViewModel (viewModel: DetailsVIewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel (viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ItemsViewModel::class)
    abstract fun bindItemsViewModel (viewModel: ItemsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel (viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel (viewModel: HomeViewModel): ViewModel
}