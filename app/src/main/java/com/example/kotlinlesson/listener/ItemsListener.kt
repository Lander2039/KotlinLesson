package com.example.kotlinlesson.listener

import android.widget.ImageView

interface ItemsListener {

    fun onClick()

    fun onElementSelected (name: String, date: String, imageView: Int)
}