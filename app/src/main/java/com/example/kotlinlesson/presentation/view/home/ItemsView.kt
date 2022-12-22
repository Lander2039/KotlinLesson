package com.example.kotlinlesson.presentation.view.home

import com.example.kotlinlesson.domain.model.ItemsModel

interface ItemsView {

    fun itemsReceived(itemsList: List<ItemsModel>)

    fun imageViewClicked(msg: Int)

    fun itemClicked(navigationDate: NavigateWithBundle)
}