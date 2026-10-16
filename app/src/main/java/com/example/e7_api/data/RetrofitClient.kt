package com.example.e7_api.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Punto único de construcción de Retrofit ("by lazy": se crea una sola
 * vez durante toda la vida de la app). Usa el cliente HTTP compartido
 * de [WikimediaHttpClient] para que el User-Agent correcto viaje en
 * cada petición.
 */
object RetrofitClient {

    private const val BASE_URL = "https://es.wikipedia.org/"

    val api: WikipediaApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(WikimediaHttpClient.instance)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WikipediaApiService::class.java)
    }
}
