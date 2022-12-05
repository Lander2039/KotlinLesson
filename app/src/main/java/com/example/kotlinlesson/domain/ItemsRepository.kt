package com.example.kotlinlesson.domain

import com.example.kotlinlesson.model.ItemsModel

interface ItemsRepository {

    fun getData(): List<ItemsModel>
}