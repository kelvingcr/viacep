package com.example.viacep.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Implementação padrão do Retrofit
 */
object ServiceProvider {

    private const val BASE_URL = "https://viacep.com.br/ws/"
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    /**
     * Aqui está fizendo que,
     * Vou fornecer uma Serviço só não sei qual ainda, por isso vou chamar de API
     * API pode ser qualquer interface. Portante que contenha os ends points
     */
    fun <API> createService(apiClass: Class<API>): API = retrofit.create(apiClass)
}