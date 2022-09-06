package com.example.quizmemes.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofitAnswer: Retrofit? = null
    private var retrofitResult: Retrofit? = null

    fun getAnswerClient(): Retrofit {
        if (retrofitAnswer == null) {
            retrofitAnswer = Retrofit.Builder()
                .baseUrl("https://jservice.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofitAnswer!!
    }

    fun getResultClient(): Retrofit {
        if (retrofitResult == null) {
            retrofitResult = Retrofit.Builder()
                .baseUrl("https://yesno.wtf/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofitResult!!
    }
}