package com.betrybe.currencyview.ui.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.betrybe.currencyview.R
import com.betrybe.currencyview.data.api.ApiService
import com.betrybe.currencyview.data.api.ApiIdlingResource
import retrofit2.HttpException
import java.io.IOException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService // Inicialize seu ApiService aqui

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Não esqueça de definir o layout

        fetchSymbols(apiService)
    }

    private fun fetchSymbols(apiService: ApiService) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Incrementar o ApiIdlingResource
                ApiIdlingResource.increment()

                // Chamar o método getSymbols na interface ApiService
                val response = apiService.getSymbols()

                // Processamento de resposta
                if (response.success) {
                    // Lógica de sucesso
                    println("Símbolos: ${response.symbols}")
                } else {
                    // Lógica de erro
                    println("Erro na resposta da API")
                }

            } catch (e: HttpException) {
                // Em caso de erro HTTP
                println("Erro HTTP: ${e.message()}")
            } catch (e: IOException) {
                // Em caso de erro de IO
                println("Erro de IO: ${e.message}")
            } finally {
                // Sempre decrementar o ApiIdlingResource
                ApiIdlingResource.decrement()
            }
        }
    }
}
