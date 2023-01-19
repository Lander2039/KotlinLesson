package com.example.kotlinlesson.presentation.adapter.listener

interface ItemsListener {

    fun onClick()

    fun onElementSelected(description: String, image: String)

    fun onDeleteClicked(description: String)
}