package com.example.kotlinlesson.di.component

import com.example.kotlinlesson.di.*
import com.example.kotlinlesson.di.factory.ScreenScope
import com.example.kotlinlesson.presentation.view.MainActivity
import com.example.kotlinlesson.presentation.view.auth.LoginFragment
import com.example.kotlinlesson.presentation.view.home.HomeFragment
import com.example.kotlinlesson.presentation.view.home.items.DetailsFragment
import com.example.kotlinlesson.presentation.view.home.items.FavoritesFragment
import com.example.kotlinlesson.presentation.view.home.items.ItemsFragment
import com.example.kotlinlesson.presentation.view.home.items.SearchFragment
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class
    ]
)

@ScreenScope
interface AppComponent {

    fun inject(loginFragment: LoginFragment)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(itemsFragment: ItemsFragment)
    fun inject(favoritesFragment: FavoritesFragment)
    fun inject(searchFragment: SearchFragment)
    fun inject(homeFragment: HomeFragment)
    fun inject(mainActivity: MainActivity)
}