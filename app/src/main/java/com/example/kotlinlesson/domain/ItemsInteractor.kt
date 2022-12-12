package com.example.kotlinlesson.domain

import com.example.kotlinlesson.presentation.model.ItemsModel

class ItemsInteractor(private val itemsRepository: ItemsRepository) {

    fun getData(): List<ItemsModel>{
        return itemsRepository.getData()
    }
}