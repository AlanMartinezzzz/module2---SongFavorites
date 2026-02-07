package com.example.songfavorites.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.songfavorites.ui.components.SongCard
import com.example.songfavorites.ui.viewmodel.HomeViewModel

/**
 * HighlightsScreen (Versión Mejorada UI)
 * Muestra las canciones favoritas con estilo Material 3.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HighlightsScreen(viewModel: HomeViewModel) {

    // 1. Observamos y Filtramos (Lógica intacta)
    val allSongs by viewModel.songs
    val favoriteSongs = allSongs.filter { it.isFavorite }

    Scaffold(
        // Barra superior centrada y con color, consistente con Home
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Highlights ⭐",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->

        // 2. Manejo de estado vacío
        if (favoriteSongs.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                // Texto de estado vacío más elegante
                Text(
                    text = "No tienes favoritos aún \n¡Ve a Home y agrega algunos!",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        } else {
            // 3. Lista con espaciado
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                // Espaciado externo e interno para que respire el diseño
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(favoriteSongs) { song ->
                    SongCard(
                        song = song,
                        onFavoriteClick = { id ->
                            viewModel.toggleFavorite(id)
                        }
                    )
                }
            }
        }
    }
}