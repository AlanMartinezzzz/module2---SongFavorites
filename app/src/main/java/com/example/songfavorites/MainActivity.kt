package com.example.songfavorites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.songfavorites.ui.screens.HomeScreen
import com.example.songfavorites.ui.theme.SongFavoritesTheme
import com.example.songfavorites.ui.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {

    /**
     * Inicializamos el ViewModel.
     * Usamos 'by viewModels()' para que el sistema de Android maneje
     * correctamente el ciclo de vida del ViewModel (que no se destruya al rotar la pantalla).
     */
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Aplicamos el tema de tu proyecto (definido en ui.theme)
            SongFavoritesTheme {
                // Surface es el contenedor base (el "lienzo")
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // LLAMADA CLAVE:
                    // Ejecutamos la pantalla HomeScreen y le pasamos nuestro ViewModel
                    HomeScreen(viewModel = homeViewModel)
                }
            }
        }
    }
}