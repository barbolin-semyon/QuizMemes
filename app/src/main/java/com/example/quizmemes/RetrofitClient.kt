package com.example.quizmemes

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofitAnswer: Retrofit? = null

    fun getAnswerClient(): Retrofit {
        if (retrofitAnswer == null) {
            retrofitAnswer = Retrofit.Builder()
                .baseUrl("https://jservice.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofitAnswer!!
    }
}