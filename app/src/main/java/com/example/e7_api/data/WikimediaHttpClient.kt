package com.example.e7_api.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Cliente HTTP compartido. Lo usan tanto Retrofit (para el JSON de
 * es.wikipedia.org) como Coil (para las fotos, que viven en
 * upload.wikimedia.org): los dos dominios son de Wikimedia y los dos
 * exigen el mismo User-Agent descriptivo, o responden 403 Forbidden.
 * Política: https://meta.wikimedia.org/wiki/User-Agent_policy
 */
object WikimediaHttpClient {

    private val userAgentInterceptor = Interceptor { chain ->
        val peticionConUserAgent = chain.request().newBuilder()
            .header(
                "User-Agent",
                "Strigiformes-TSP/1.0 (proyecto academico UNAM; " +
                    "https://github.com/omarcrcrs/tsp2-p2-iuestados) OkHttp"
            )
            .build()
        chain.proceed(peticionConUserAgent)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    val instance: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(userAgentInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}
