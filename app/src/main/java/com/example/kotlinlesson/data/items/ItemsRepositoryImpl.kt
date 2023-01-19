package com.example.kotlinlesson.data.items

import com.example.kotlinlesson.data.ApiService
import com.example.kotlinlesson.domain.items.ItemsRepository
import com.example.kotlinlesson.domain.model.ItemsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ItemsRepository {
    override suspend fun getData(): List<ItemsModel> {
        return withContext(Dispatchers.IO) {

            val response = apiService.getData()
            response.body()?.sampleList!!.let {
                it.map {
                    ItemsModel(it.description, it.imageUrl)
                }
            } ?: kotlin.run {
                emptyList()
            }
        }
    }
}