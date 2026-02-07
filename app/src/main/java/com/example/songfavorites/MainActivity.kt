package com.example.songfavorites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.songfavorites.ui.screens.HighlightsScreen
import com.example.songfavorites.ui.screens.HomeScreen
import com.example.songfavorites.ui.theme.SongFavoritesTheme
import com.example.songfavorites.ui.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {

    // Inicializamos el ViewModel aquí para que viva mientras la app esté abierta
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SongFavoritesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Llamamos a la navegación principal
                    MainAppNavigation(viewModel = homeViewModel)
                }
            }
        }
    }
}

/*
 * MainAppNavigation
 * Este componente maneja la lógica de cambio de pantallas (Home <-> Highlights)
 */
@Composable
fun MainAppNavigation(viewModel: HomeViewModel) {

    // ESTADO: Controla qué pantalla se muestra (0 = Home, 1 = Highlights)
    var currentScreenIndex by remember { mutableStateOf(0) }

    Scaffold(
        // BARRA INFERIOR (Bottom Navigation)
        bottomBar = {
            NavigationBar {
                // Opción 1: Home
                NavigationBarItem(
                    selected = currentScreenIndex == 0,
                    onClick = { currentScreenIndex = 0 },
                    label = { Text("Home") },
                    icon = {
                        Icon(Icons.Default.Home, contentDescription = "Ir a Inicio")
                    }
                )

                // Opción 2: Highlights (Destacados)
                NavigationBarItem(
                    selected = currentScreenIndex == 1,
                    onClick = { currentScreenIndex = 1 },
                    label = { Text("Highlights") },
                    icon = {
                        Icon(Icons.Default.Star, contentDescription = "Ir a Favoritos")
                    }
                )
            }
        }
    ) { paddingValues ->
        // CONTENIDO PRINCIPAL
        // Usamos Box con padding para respetar el espacio de la barra inferior
        Box(modifier = Modifier.padding(paddingValues)) {
            when (currentScreenIndex) {
                0 -> HomeScreen(viewModel = viewModel)
                1 -> HighlightsScreen(viewModel = viewModel)
            }
        }
    }
}