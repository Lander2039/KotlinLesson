package com.example.kotlinlesson.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinlesson.domain.items.ItemsInteractor
import com.example.kotlinlesson.presentation.view.ItemsViewModel

class ItemsViewModelFactory(private val itemsInteractor: ItemsInteractor): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemsViewModel(itemsInteractor) as T
    }
}