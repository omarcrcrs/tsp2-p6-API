package com.example.e7_api.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e7_api.data.LechuzasRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * ViewModel propio de la pantalla de lechuzas. Expone el estado de la
 * petición como [StateFlow] para que Compose se recomponga solo cuando
 * cambia, y sobrevive a cambios de configuración (p. ej. rotar la
 * pantalla) porque el ciclo de vida lo administra el framework.
 */
class LechuzasViewModel(
    private val repositorio: LechuzasRepository = LechuzasRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<LechuzasUiState>(LechuzasUiState.Cargando)
    val uiState: StateFlow<LechuzasUiState> = _uiState.asStateFlow()

    init {
        cargarLechuzas()
    }

    /** Dispara (o reintenta) la petición al servidor. */
    fun cargarLechuzas() {
        viewModelScope.launch {
            _uiState.value = LechuzasUiState.Cargando
            _uiState.value = try {
                val lechuzas = repositorio.obtenerLechuzas()
                if (lechuzas.isEmpty()) {
                    LechuzasUiState.Error("Wikipedia no devolvió imágenes para estas especies.")
                } else {
                    LechuzasUiState.Exito(lechuzas)
                }
            } catch (e: IOException) {
                LechuzasUiState.Error("Sin conexión a internet. Revisa tu red e intenta de nuevo.")
            } catch (e: HttpException) {
                LechuzasUiState.Error("El servidor respondió con un error (código ${e.code()}).")
            } catch (e: Exception) {
                LechuzasUiState.Error("Ocurrió un error inesperado: ${e.localizedMessage ?: e.toString()}")
            }
        }
    }
}
