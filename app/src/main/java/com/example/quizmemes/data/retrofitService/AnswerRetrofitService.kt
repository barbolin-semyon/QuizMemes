package com.example.quizmemes.data.retrofitService

import com.example.quizmemes.data.emptities.Questions
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AnswerRetrofitService {
    @GET("random?")
    fun getRandomQuestion(@Query("count") count: Int): Call<Questions>
}