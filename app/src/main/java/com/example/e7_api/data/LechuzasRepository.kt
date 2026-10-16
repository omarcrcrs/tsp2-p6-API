package com.example.e7_api.data

import com.example.e7_api.model.Lechuza

/**
 * Traduce la respuesta "cruda" de Wikipedia al modelo de dominio [Lechuza]
 * que consume la UI, y decide qué especies se piden. Si una página no
 * trae miniatura (thumbnail), se descarta: la lista final solo trae
 * lechuzas con foto real.
 */
class LechuzasRepository(
    private val api: WikipediaApiService = RetrofitClient.api
) {

    /** Especies (título real del artículo en Wikipedia) a consultar. */
    private val especies = listOf(
        "Tyto alba",
        "Bubo bubo",
        "Athene noctua",
        "Strix aluco",
        "Asio otus",
        "Asio flammeus",
        "Bubo virginianus",
        "Strix nebulosa",
        "Bubo scandiacus",
        "Otus scops",
        "Glaucidium brasilianum",
        "Pulsatrix perspicillata",
        "Strix varia",
        "Megascops choliba",
        "Ninox novaeseelandiae"
    )

    suspend fun obtenerLechuzas(): List<Lechuza> {
        val titulos = especies.joinToString("|")
        val respuesta = api.obtenerPaginas(titulos = titulos)
        val paginas = respuesta.query?.pages.orEmpty()

        return paginas
            .filter { pagina -> pagina.missing != true && !pagina.thumbnail?.source.isNullOrBlank() }
            .map { pagina ->
                Lechuza(
                    id = pagina.pageid,
                    titulo = pagina.title,
                    descripcion = pagina.extract?.trim().orEmpty(),
                    imagenUrl = pagina.thumbnail!!.source
                )
            }
            .distinctBy { it.id }
            .sortedBy { it.titulo }
    }
}
