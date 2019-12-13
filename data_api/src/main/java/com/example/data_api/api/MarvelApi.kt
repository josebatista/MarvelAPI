package com.example.data_api.api

import com.example.data.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    fun load(@Query("offset") offset: Int? = 0): Call<Response>

    @GET("characters/{id}")
    fun loadCharacter(@Path("id") id: Int): Call<Response>

}