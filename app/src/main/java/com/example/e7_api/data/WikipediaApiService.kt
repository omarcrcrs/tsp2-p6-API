package com.example.e7_api.data

import com.example.e7_api.model.WikiResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Endpoint "query" de la API pública de Wikipedia. No requiere API key.
 * Documentación: https://www.mediawiki.org/wiki/API:Main_page
 */
interface WikipediaApiService {

    @GET("w/api.php")
    suspend fun obtenerPaginas(
        @Query("titles") titulos: String,
        @Query("action") accion: String = "query",
        @Query("format") formato: String = "json",
        @Query("formatversion") formatVersion: Int = 2,
        @Query("prop") propiedades: String = "pageimages|extracts",
        @Query("piprop") piprop: String = "thumbnail",
        @Query("pithumbsize") tamanoMiniatura: Int = 600,
        @Query("exintro") soloIntro: Int = 1,
        @Query("explaintext") textoPlano: Int = 1,
        @Query("exchars") maxCaracteres: Int = 400,
        @Query("redirects") seguirRedirecciones: Int = 1
    ): WikiResponse
}
