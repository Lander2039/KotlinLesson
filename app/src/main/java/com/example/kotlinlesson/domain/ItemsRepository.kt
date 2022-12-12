package com.example.kotlinlesson.domain

import com.example.kotlinlesson.presentation.model.ItemsModel

interface ItemsRepository {

    fun getData(): List<ItemsModel>
}