package com.example.kotlinlesson.data.items

import android.annotation.SuppressLint
import android.util.Log
import com.example.kotlinlesson.data.database.FavoritesEntity
import com.example.kotlinlesson.data.database.ItemsEntity
import com.example.kotlinlesson.data.database.dao.ItemsDAO
import com.example.kotlinlesson.data.service.ApiService
import com.example.kotlinlesson.data.service.ApiServiceSecond
import com.example.kotlinlesson.domain.items.ItemsRepository
import com.example.kotlinlesson.domain.model.FavoriteModel
import com.example.kotlinlesson.domain.model.ItemsModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class ItemsRepositoryImpl @Inject constructor(
    @Named("FIRST") private val apiService: ApiService,
    @Named("SECOND") private val apiServiceSecond: ApiServiceSecond,
    private val itemsDAO: ItemsDAO
) : ItemsRepository {

    private val compositeDisposable= CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun getData(): Completable {

        return itemsDAO.doesItemsEntityExist()
            .subscribeOn(Schedulers.io())
            .doAfterNext {
                if (!it){
                    val response = apiService.getData()
                    val getData = response.subscribeOn(Schedulers.io())
                        .doAfterSuccess {
                            it.sampleList.map {
                                val itemsEntity =
                                    ItemsEntity(
                                        java.util.Random().nextInt(),
                                        it.description,
                                        it.imageUrl
                                    )
                                itemsDAO.insertItemsEntity(itemsEntity)
                            }
                        }
                        .doOnError {
                            Log.w("error", "when making request")
                        }
                        .ignoreElement()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
                    compositeDisposable.add(getData)
                }
                compositeDisposable.dispose()
            }
            .ignoreElements()
            .observeOn(AndroidSchedulers.mainThread())
    }

    @SuppressLint("CheckResult")
    override fun showData(): Observable<List<ItemsModel>> {
        val itemsEntity = itemsDAO.getItemsEntities()
        return itemsEntity.subscribeOn(Schedulers.io())
            .map {
                it.map {
                    ItemsModel(it.id, it.description, it.imageUrl, it.isFavorite ?: false)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
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