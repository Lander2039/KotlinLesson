package com.example.kotlinlesson.presentation.view

import com.example.kotlinlesson.R
import com.example.kotlinlesson.domain.ItemsInteractor
import com.example.kotlinlesson.model.ItemsModel

class ItemsPresenter(private val itemsView: ItemsView,
private val itemsInteractor: ItemsInteractor) {

    fun getDate(){
        val listItems = itemsInteractor.getData()
        itemsView.dataReceived(listItems)
    }

    fun imageViewClicked(){
        itemsView.imageViewClicked(R.string.Imageviewclicked)
    }

    fun elementSelected(name: String, date: String, imageView: Int){
        itemsView.goToDetails(name,date,imageView)
    }

}