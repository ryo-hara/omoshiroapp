package com.example.omoshiro_app.model

import android.util.Log
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit
import com.google.gson.GsonBuilder

class DivinationRepository {

    private var divinationResult: DivinationResult? = null;

    fun getDivination(): DivinationResult?{
        return divinationResult;
    }

    fun requestData(onSuccess: () -> Unit, onFailure: () -> Unit){

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://script.google.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val service: DivinationService = retrofit.create(DivinationService::class.java)

        val divinationCall: Call<DivinationResult?>? = service.getResult()
        divinationCall?.enqueue(object : Callback<DivinationResult?> {
            override fun onResponse(call: Call<DivinationResult?>?, response: Response<DivinationResult?>) {
                val result: DivinationResult? = response.body()
                Log.d("====", "=====Success" + result.toString())
                divinationResult = result
                onSuccess.invoke()
            }

            override fun onFailure(call: Call<DivinationResult?>?, t: Throwable?) {
                Log.d("====", "=====Error" + t.toString())
                onFailure.invoke()
            }
        })

    }

}

interface DivinationService {
    // MEMO: ここ直打ちはなんとかしたいよなぁ…
    @GET("macros/s/AKfycbyyr2CIr5sSvZoMoYwvGo1Af7pMiNT-kE1Tx1_m_0090uCZoivFhOU0KqL6VkjTyjCxpA/exec")
    fun getResult(): Call<DivinationResult?>
}
