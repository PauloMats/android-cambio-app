package com.betrybe.currencyview.ui.views.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.betrybe.currencyview.R
import com.betrybe.currencyview.common.ApiIdlingResource
import com.betrybe.currencyview.data.api.apiRetrofit
import com.betrybe.currencyview.ui.adapters.RatesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val currencyState: TextView by lazy { findViewById(R.id.select_currency_state) }
    private val currencySelection: AutoCompleteTextView by lazy { findViewById(R.id.currency_selection_input_layout) }
    private val recyclerViewRates: RecyclerView by lazy { findViewById(R.id.currency_rates_state) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewRates.layoutManager = LinearLayoutManager(this)

        fetchCurrencyData()
    }

    private fun fetchCurrencyData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                ApiIdlingResource.increment()
                val apiService = apiRetrofit.instance

                // Chamada para buscar os símbolos de moeda
                val apiRes = apiService.getSymbol()
                Log.d("resposta", "apiKey:20EyGYInJopKHhX7HzFTsiWv41CbMtdF")
                if (apiRes.isSuccessful) {
                    val data = apiRes.body()

                    if (data != null) {
                        withContext(Dispatchers.Main) {
                            val symbols = data.symbols.keys.toList()
                            Log.d("symbols", "$symbols")
                            val adapter = ArrayAdapter(baseContext, R.layout.coin_list, symbols)
                            currencySelection.setAdapter(adapter)
                            currencyState.visibility = View.VISIBLE
                        }
                    }
                }
                val apiResRates = apiService.getLatestRates("BRL")
                Log.d("resposta", "apiKey:20EyGYInJopKHhX7HzFTsiWv41CbMtdF")
                if (apiResRates.isSuccessful) {
                    val data = apiResRates.body()

                    if (data != null) {
                        withContext(Dispatchers.Main) {
                            val rates = data.rates.keys.toList()
                            Log.d("rates", "$rates")
                            val ratesAdapter = ArrayAdapter(baseContext, R.layout.item_rate, rates)
                            currencySelection.setAdapter(ratesAdapter)
                            currencyState.visibility = View.VISIBLE
                        }
                    }
                }



                ApiIdlingResource.decrement()
            } catch (e: HttpException) {
                ApiIdlingResource.decrement()
                Log.e("Error", "HttpException: ${e.message()}")
            } catch (e: IOException) {
                ApiIdlingResource.decrement()
                Log.e("Error", "IOException: ${e.message}")
            }
        }
    }
}