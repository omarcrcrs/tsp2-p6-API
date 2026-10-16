package com.example.e7_api.model

/**
 * Modelo de dominio que consume la UI. Se arma a partir de [WikiPage],
 * quedándonos solo con lo que la pantalla necesita mostrar.
 */
data class Lechuza(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val imagenUrl: String
)
