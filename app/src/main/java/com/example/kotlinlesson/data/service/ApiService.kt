package com.example.kotlinlesson.data.service

import com.example.kotlinlesson.data.model.ItemsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {


    @GET("/nkuYRM")
    fun getData(): Single<ItemsResponse>
}