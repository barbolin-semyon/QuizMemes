package com.example.quizmemes.data.retrofitService

import com.example.quizmemes.data.emptities.GIFImage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ResultRetrofitService {
    @GET("api?")
    fun getResult(@Query("forced") forced: String): Call<GIFImage>
}