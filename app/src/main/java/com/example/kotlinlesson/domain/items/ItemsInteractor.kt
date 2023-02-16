package com.example.kotlinlesson.domain.items

import com.example.kotlinlesson.domain.model.FavoriteModel
import com.example.kotlinlesson.domain.model.ItemsModel
import io.reactivex.Completable
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemsInteractor @Inject constructor(private val itemsRepository: ItemsRepository) {

    fun getData() : Completable {
        return itemsRepository.getData()
    }

    fun showData(): Observable<List<ItemsModel>> {
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