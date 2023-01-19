package com.example.kotlinlesson.domain.items

import com.example.kotlinlesson.domain.model.ItemsModel
import javax.inject.Inject

class ItemsInteractor @Inject constructor(private val itemsRepository: ItemsRepository) {

    suspend fun getData(){
        return itemsRepository.getData()
    }

    suspend fun showData(): List<ItemsModel>{
        return itemsRepository.showData()
    }

    suspend fun deleteItemByDescription(description: String){
        itemsRepository.deleteItemByDescription(description)
    }

    suspend fun findItem(searchText: String) : ItemsModel{
        return itemsRepository.findItemByDescription(searchText)
    }
}