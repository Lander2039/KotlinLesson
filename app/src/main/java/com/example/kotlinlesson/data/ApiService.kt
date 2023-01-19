package com.example.kotlinlesson.data

import com.example.kotlinlesson.data.model.ItemsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/nkuYRM")
    suspend fun getData(): Response<ItemsResponse>
}