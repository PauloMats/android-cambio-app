package com.betrybe.currencyview.data.api

import com.betrybe.currencyview.data.models.CurrencySymbolResponse
import com.betrybe.currencyview.data.models.CurrencyRateResponse
import retrofit2.http.GET

// Interface para a API do Retrofit
interface ApiService {
    @GET("/exchangerates_data/symbols")
    suspend fun getSymbols(): CurrencySymbolResponse

    @GET("/exchangerates_data/latest ")
    suspend fun getLatest(): CurrencyRateResponse

}
