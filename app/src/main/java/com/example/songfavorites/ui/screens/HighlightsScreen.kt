package com.example.songfavorites.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.songfavorites.ui.components.SongCard
import com.example.songfavorites.ui.viewmodel.HomeViewModel

/**
 * HighlightsScreen (Parte 2)
 * Esta pantalla muestra SOLO las canciones marcadas como favoritas.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HighlightsScreen(viewModel: HomeViewModel) {

    // 1. Observamos la lista completa del ViewModel
    // Usamos 'by' para acceder al valor directamente
    val allSongs by viewModel.songs

    // 2. FILTRADO: Creamos una sub-lista solo con las favoritas (isFavorite == true)
    val favoriteSongs = allSongs.filter { it.isFavorite }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Highlights ⭐", fontWeight = FontWeight.Bold)
                }
            )
        }
    ) { paddingValues ->

        // 3. Manejo de estado vacío: Si no hay favoritas, mostramos un mensaje.
        if (favoriteSongs.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Aún no tienes favoritos",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        } else {
            // 4. Si hay favoritas, las mostramos en la lista
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(favoriteSongs) { song ->
                    SongCard(
                        song = song,
                        // Reutilizamos la misma lógica del ViewModel.
                        // Al dar click aquí, se quita de favoritos y desaparece de esta lista automáticamente.
                        onFavoriteClick = { id ->
                            viewModel.toggleFavorite(id)
                        }
                    )
                }
            }
        }
    }
}