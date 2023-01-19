package com.example.kotlinlesson.domain.items

import com.example.kotlinlesson.domain.model.ItemsModel

interface ItemsRepository {

    suspend fun getData()

    suspend fun showData(): List<ItemsModel>

    suspend fun deleteItemByDescription(description: String)

    suspend fun findItemByDescription(searchText: String) : ItemsModel


}