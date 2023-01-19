package com.example.kotlinlesson.data.items

import android.util.Log
import com.example.kotlinlesson.data.service.ApiService
import com.example.kotlinlesson.data.service.ApiServiceSecond
import com.example.kotlinlesson.domain.items.ItemsRepository
import com.example.kotlinlesson.domain.model.ItemsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class ItemsRepositoryImpl @Inject constructor(
   @Named("FIRST") private val apiService: ApiService,
   @Named("SECOND") private val apiServiceSecond: ApiServiceSecond
) : ItemsRepository {
    override suspend fun getData(): List<ItemsModel> {
        return withContext(Dispatchers.IO) {
            val responseSecond = apiServiceSecond.getPhotoByID(3)
            Log.w("SECOND RESPONSE", responseSecond.body()?.title.toString())

            val responseSecondQuery = apiServiceSecond.getPhotoByTitle("officia porro iure quia iusto qui ipsa ut modi")
            


            val response = apiService.getData()
            response.body()?.sampleList?.let {
                it.map {
                    ItemsModel(it.description, it.imageUrl)
                }
            } ?: kotlin.run {
                emptyList()
            }
        }
    }
}