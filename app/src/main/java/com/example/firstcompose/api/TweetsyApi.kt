package com.example.firstcompose.api

import com.example.firstcompose.model.TweetsResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsyApi {

    @GET("/v3/b/6801ff618960c979a587cba9?meta=false")
   suspend fun getTweets(@Header("X-JSON-PATH") category: String):    Response<List<TweetsResponseItem>>


    @Headers("X-JSON-PATH: categories..name")
    @GET("/v3/b/6801ff618960c979a587cba9?meta=false")
    suspend fun getCategories(): Response<List<String>>
}