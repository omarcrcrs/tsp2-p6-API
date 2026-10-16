package com.example.e7_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e7_api.ui.theme.E7_apiTheme
import com.example.e7_api.viewmodel.LechuzasUiState
import com.example.e7_api.viewmodel.LechuzasViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            E7_apiTheme {
                PantallaLechuzas()
            }
        }
    }
}

/**
 * Pantalla única de la app: observa el [LechuzasViewModel] y dibuja uno
 * de los tres estados personalizados según lo que vaya pasando con la
 * petición al servidor.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLechuzas(viewModel: LechuzasViewModel = viewModel(), modifier: Modifier = Modifier) {
    val estado by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Strigiformes") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (val estadoActual = estado) {
                is LechuzasUiState.Cargando -> EstadoCargando()
                is LechuzasUiState.Error -> EstadoError(
                    mensaje = estadoActual.mensaje,
                    onReintentar = viewModel::cargarLechuzas
                )
                is LechuzasUiState.Exito -> ListaLechuzas(lechuzas = estadoActual.lechuzas)
            }
        }
    }
}
