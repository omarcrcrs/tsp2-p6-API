package com.example.e7_api.model

/**
 * Jerarquía de clases que refleja tal cual la respuesta JSON de la API de
 * Wikipedia (action=query, con formatversion=2 para que "pages" llegue
 * como una lista en vez de un objeto indexado por id de página).
 *
 * Documentación: https://www.mediawiki.org/wiki/API:Query
 */
data class WikiResponse(
    val query: WikiQuery? = null
)

data class WikiQuery(
    val pages: List<WikiPage> = emptyList()
)

data class WikiPage(
    val pageid: Int = 0,
    val title: String = "",
    val extract: String? = null,
    val thumbnail: WikiThumbnail? = null,
    val missing: Boolean? = null
)

data class WikiThumbnail(
    val source: String = "",
    val width: Int = 0,
    val height: Int = 0
)
