package com.example.e7_api.viewmodel

import com.example.e7_api.model.Lechuza

/**
 * Los tres estados posibles de la pantalla mientras se hace la petición
 * al servidor. Cada uno tiene su propia representación visual
 * "personalizada" (ver EstadosPersonalizados.kt y LechuzaCard.kt).
 */
sealed interface LechuzasUiState {
    data object Cargando : LechuzasUiState
    data class Exito(val lechuzas: List<Lechuza>) : LechuzasUiState
    data class Error(val mensaje: String) : LechuzasUiState
}
