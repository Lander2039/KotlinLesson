package com.example.kotlinlesson.data.service

import com.example.kotlinlesson.data.model.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceSecond {

    @GET("/photos/{id}")
    suspend fun getPhotoByID(@Path("id") photoId: Int): Response<PhotoResponse>

    @GET("/photos")
    suspend fun getPhotoByTitle(@Query("title") title: String): Response<List<PhotoResponse>>
}