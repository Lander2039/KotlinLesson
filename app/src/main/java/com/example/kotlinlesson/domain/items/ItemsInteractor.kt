package com.example.kotlinlesson.domain.items

import com.example.kotlinlesson.domain.model.ItemsModel
import javax.inject.Inject

class ItemsInteractor @Inject constructor(private val itemsRepository: ItemsRepository) {

    suspend fun getData(): List<ItemsModel>{
        return itemsRepository.getData()
    }
}