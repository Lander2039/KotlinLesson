package com.example.kotlinlesson.presentation.view

import com.example.kotlinlesson.model.ItemsModel

interface ItemsView {

    fun dataReceived(list: List<ItemsModel>)

    fun imageViewClicked(msg: Int)

    fun goToDetails(name: String, date: String, imageView: Int)
}