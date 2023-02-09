package com.example.kotlinlesson.domain.items

import com.example.kotlinlesson.domain.model.FavoriteModel
import com.example.kotlinlesson.domain.model.ItemsModel
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {

    suspend fun getData()

    suspend fun showData(): Flow<List<ItemsModel>>

    suspend fun deleteItemByDescription(description: String)

    suspend fun findItemByDescription(searchText: String): ItemsModel

    suspend fun favClicked(itemsModel: ItemsModel, isFavorite: Boolean)

    suspend fun getFavorites(): List<FavoriteModel>

}