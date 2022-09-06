package com.example.quizmemes.data.emptities

import com.google.gson.annotations.SerializedName


class Questions : ArrayList<Question>()


data class Question(
    @SerializedName("question")
    val text: String? = null,

    @SerializedName("answer")
    val answer: String? = null
)
