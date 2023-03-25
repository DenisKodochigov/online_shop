package com.example.online_shop.data.api

import com.example.online_shop.data.api.dto.FlashDTO
import com.example.online_shop.data.api.dto.LatestDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoskyAPI {

    @Headers("Accept: application/json", "Content-type: application/json")
    @GET("/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatest(
        @Query("firstName") firstName:String, @Query("password") password: String,): LatestDTO

    @Headers("Accept: application/json", "Content-type: application/json")
    @GET("/v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlash(): FlashDTO

    @Headers("Accept: application/json", "Content-type: application/json")
    @GET("/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getBrands(): LatestDTO
}
