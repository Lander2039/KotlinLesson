package com.example.kotlinlesson.domain.items

import com.example.kotlinlesson.domain.model.ItemsModel

interface ItemsRepository {

    fun getData(): List<ItemsModel>
}