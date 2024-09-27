package com.betrybe.currencyview.ui.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.betrybe.currencyview.R
import retrofit2.HttpException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.betrybe.currencyview.common.ApiIdlingResource
import kotlinx.coroutines.withContext
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.ArrayAdapter
import android.util.Log
import java.io.IOException
import com.betrybe.currencyview.data.api.apiRetrofit


class MainActivity : AppCompatActivity() {

    private val currencyState : TextView by lazy { findViewById(R.id.select_currency_state) }
    private val currencySelection : AutoCompleteTextView by lazy { findViewById(R.id.currency_selection_input_layout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                ApiIdlingResource.increment()
                val apiService = apiRetrofit.instance
                val apiRes = apiService.getSymbol()
                Log.d("resposta", "apiKey:20EyGYInJopKHhX7HzFTsiWv41CbMtdF")
                if(apiRes.isSuccessful) {
                    val data = apiRes.body()

                    if(data != null) {
                        withContext(Dispatchers.Main) {
                            val symbols = data.symbols.keys.toList()
                            Log.d("symbols", "$symbols")
                            val adapter = ArrayAdapter(baseContext, R.layout.coin_list, symbols)
                            currencySelection.setAdapter(adapter)
                            currencyState.visibility = View.VISIBLE
                        }
                    }
                }
                ApiIdlingResource.decrement()
            } catch (e: HttpException) {
                ApiIdlingResource.decrement()

            } catch (e: IOException) {
                ApiIdlingResource.decrement()
            }
        }
    }
}
