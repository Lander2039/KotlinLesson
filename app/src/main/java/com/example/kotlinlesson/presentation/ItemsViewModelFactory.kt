package com.example.kotlinlesson.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinlesson.domain.ItemsInteractor

class ItemsViewModelFactory(private val itemsInteractor: ItemsInteractor): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemsViewModel(itemsInteractor) as T
    }
}