package com.example.kotlinlesson.presentation.view.home

import com.example.kotlinlesson.R
import com.example.kotlinlesson.domain.items.ItemsInteractor
import javax.inject.Inject

class ItemsPresenter @Inject constructor(
    private val itemsInteractor: ItemsInteractor
) {
    private lateinit var itemsView: ItemsView

    fun setView(itemsFragment: ItemsFragment) {
        itemsView = itemsFragment
    }

    fun getItems() {
        val items = itemsInteractor.getData()
        itemsView.itemsReceived(items)
    }

    fun imageViewClicked() {
        itemsView.imageViewClicked(R.string.Imageviewclicked)
    }

    fun itemClicked(name: String, date: String, imageView: Int) {
        itemsView.itemClicked(NavigateWithBundle(name, date, imageView))
    }
}

data class NavigateWithBundle(
    val name: String,
    val date: String,
    val image: Int,
)