package com.betrybe.currencyview.data.api

import com.betrybe.currencyview.data.models.CurrencySymbolResponse
import com.betrybe.currencyview.data.models.CurrencyRateResponse
import retrofit2.http.Query
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("apiKey:20EyGYInJopKHhX7HzFTsiWv41CbMtdF")
    @GET("symbols")
    suspend fun getSymbol(): Response<CurrencySymbolResponse>

    @Headers("apiKey:20EyGYInJopKHhX7HzFTsiWv41CbMtdF")
    @GET("latest")
    suspend fun getLatestRates(@Query("base") base: String): Response<CurrencyRateResponse>

}
