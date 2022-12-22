package com.example.kotlinlesson.presentation.view.home

interface DetailsView {

    fun userLoggedOut()

    fun displayItemDate(name: String, date: String, imageView: Int)
}