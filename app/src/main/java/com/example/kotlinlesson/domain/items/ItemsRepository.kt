package com.example.kotlinlesson.domain.items

import com.example.kotlinlesson.domain.model.ItemsModel

interface ItemsRepository {

    suspend fun getData(): List<ItemsModel>
}