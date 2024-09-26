package com.betrybe.currencyview.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRetrofit {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.apilayer.com/") // base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)

}