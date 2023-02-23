package com.example.kotlinlesson.data.items

import android.util.Log
import com.example.kotlinlesson.data.database.FavoritesEntity
import com.example.kotlinlesson.data.database.ItemsEntity
import com.example.kotlinlesson.data.database.dao.ItemsDAO
import com.example.kotlinlesson.data.service.ApiService
import com.example.kotlinlesson.data.service.ApiServiceSecond
import com.example.kotlinlesson.domain.items.ItemsRepository
import com.example.kotlinlesson.domain.model.FavoriteModel
import com.example.kotlinlesson.domain.model.ItemsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class ItemsRepositoryImpl @Inject constructor(
    @Named("FIRST") private val apiService: ApiService,
    @Named("SECOND") private val apiServiceSecond: ApiServiceSecond,
    private val itemsDAO: ItemsDAO
) : ItemsRepository {
    override suspend fun getData() {
        return withContext(Dispatchers.IO) {
           val itemExist =  itemsDAO.doesItemsEntityExist()
                if (!itemExist) {
                    Log.w("getData", "data not exists")
                    val response = apiService.getData()
                    Log.w("data", response.body()?.sampleList.toString())
                    response.body()?.sampleList?.let {
                        it.map {
                            val itemsEntity =
                                ItemsEntity(
                                    java.util.Random().nextInt(),
                                    it.description,
                                    it.imageUrl
                                )
                            itemsDAO.insertItemsEntity(itemsEntity)

                    }
                }
            }
        }
    }

    override suspend fun showData(): List<ItemsModel> {
        return withContext(Dispatchers.IO) {
            val itemsEntity = itemsDAO.getItemsEntities()
                itemsEntity.map { item ->
                    ItemsModel(item.id, item.description, item.imageUrl, item.isFavorite ?: false)
                }
            }
    }

    override suspend fun deleteItemByDescription(description: String) {
        withContext(Dispatchers.IO) {
            itemsDAO.deleteItemEntityByDescription(description)
        }
    }

    override suspend fun findItemByDescription(searchText: String): ItemsModel {
        return withContext(Dispatchers.IO) {
            val itemsEntity = itemsDAO.findItemEntityByDescription(searchText)
            ItemsModel(
                itemsEntity.id,
                itemsEntity.description,
                itemsEntity.imageUrl,
                itemsEntity.isFavorite ?: false
            )
        }
    }

    override suspend fun favClicked(itemsModel: ItemsModel, isFavorite: Boolean) {
        return withContext(Dispatchers.IO) {
            itemsDAO.insertFavoritesEntity(
                FavoritesEntity(
                    itemsModel.id,
                    itemsModel.description,
                    itemsModel.image
                )
            )
            itemsDAO.addToFavorite(itemsModel.description, isFavorite)
        }
    }

    override suspend fun getFavorites(): List<FavoriteModel> {
        return withContext(Dispatchers.IO) {
            val favoritesEntity = itemsDAO.getFavoritesEntity()
            favoritesEntity.map {
                FavoriteModel(it.description, it.imageUrl)
            }
        }
    }
}