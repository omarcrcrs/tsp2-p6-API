package com.example.e7_api

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.example.e7_api.data.WikimediaHttpClient

/**
 * Coil llama automáticamente a newImageLoader() la primera vez que hace
 * falta cargar una imagen, y reutiliza el resultado durante toda la app.
 * Sin esta clase, Coil arma su propio cliente HTTP por separado del de
 * Retrofit, con un User-Agent genérico que Wikimedia rechaza (403), así
 * que las fotos nunca cargan aunque los datos de texto sí lleguen bien.
 */
class LechuzasApplication : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .okHttpClient(WikimediaHttpClient.instance)
            .build()
    }
}
