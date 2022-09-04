package com.example.quizmemes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel : ViewModel() {

    private val client =
        RetrofitClient.getAnswerClient().create(AnswerRetrofitService::class.java)

    private val _question =  MutableLiveData<Question?>(null)
    val question: LiveData<Question?>
        get() = _question

    fun getQuestion() {
        client.getRandomQuestion(1).enqueue(object : Callback<Questions> {
            override fun onResponse(
                call: Call<Questions>,
                response: Response<Questions>
            ) {
                response.body() .let { _question.value = response.body()!![0] }
            }

            override fun onFailure(call: Call<Questions>, t: Throwable) {
                Log.i("QWE", t.message.toString())
            }

        })
    }
}