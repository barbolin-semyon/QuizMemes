package com.example.quizmemes.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizmemes.data.retrofitService.AnswerRetrofitService
import com.example.quizmemes.data.emptities.Question
import com.example.quizmemes.data.emptities.Questions
import com.example.quizmemes.data.RetrofitClient
import com.example.quizmemes.data.emptities.GIFImage
import com.example.quizmemes.data.retrofitService.ResultRetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel : ViewModel() {

    private val _question =  MutableLiveData<Question?>(null)
    val question: LiveData<Question?>
        get() = _question

    private val _urlResult =  MutableLiveData<GIFImage?>(null)
    val urlResult: LiveData<GIFImage?>
        get() = _urlResult

    fun getQuestion() {

        val client =
            RetrofitClient.getAnswerClient().create(AnswerRetrofitService::class.java)

        client.getRandomQuestion(1).enqueue(object : Callback<Questions> {
            override fun onResponse(
                call: Call<Questions>,
                response: Response<Questions>
            ) {
                response.body() .let {
                    _question.value = response.body()!![0]
                    Log.i("QWE", "Answer: ${_question.value!!.answer}")
                }
            }

            override fun onFailure(call: Call<Questions>, t: Throwable) {
                Log.i("QWE", t.message.toString())
            }

        })
    }

    fun getUrlGif(isRight: Boolean) {
        val client =
            RetrofitClient.getResultClient().create(ResultRetrofitService::class.java)

        val force = if (isRight) "yes" else "no"

        client.getResult(force).enqueue(object : Callback<GIFImage> {
            override fun onResponse(call: Call<GIFImage>, response: Response<GIFImage>) {
                Log.i("QWE", "getUrlGif Responce: ${response.body()}")
                _urlResult.value = response.body()
            }

            override fun onFailure(call: Call<GIFImage>, t: Throwable) {
                Log.i("QWE", "getUrlGif Exception: $t")
            }

        })
    }
}