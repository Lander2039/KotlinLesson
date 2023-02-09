package com.example.kotlinlesson.domain.items

import com.example.kotlinlesson.domain.model.FavoriteModel
import com.example.kotlinlesson.domain.model.ItemsModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemsInteractor @Inject constructor(private val itemsRepository: ItemsRepository) {

    suspend fun getData() {
        return itemsRepository.getData()
    }

    suspend fun showData(): Flow<List<ItemsModel>> {
        return itemsRepository.showData()
    }

    suspend fun deleteItemByDescription(description: String) {
        itemsRepository.deleteItemByDescription(description)
    }

    suspend fun findItem(searchText: String): ItemsModel {
        return itemsRepository.findItemByDescription(searchText)
    }

    suspend fun onFavClicked(description: String, isFavorite: Boolean) {
        val foundItem = itemsRepository.findItemByDescription(description)
        itemsRepository.favClicked(foundItem, isFavorite)
//        itemsRepository.deleteItemByDescription(description)
    }

    suspend fun getFavorites(): List<FavoriteModel> {
        return itemsRepository.getFavorites()
    }
}