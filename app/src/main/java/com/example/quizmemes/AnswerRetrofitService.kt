package com.example.quizmemes

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AnswerRetrofitService {
    @GET("random?")
    fun getRandomQuestion(@Query("count") count: Int): Call<Questions>
}