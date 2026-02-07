package com.example.songfavorites.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.songfavorites.ui.components.SongCard
import com.example.songfavorites.ui.viewmodel.HomeViewModel

/**
 * HomeScreen (Versión Mejorada UI)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    // 1. Observamos la lista
    val songsList by viewModel.songs

    Scaffold(
        //Usamos CenterAlignedTopAppBar para un look más simétrico
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "StreamUI 🎵",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.surface // Color de fondo base
    ) { paddingValues ->

        // 2. LazyColumn con mejor espaciado
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            // Padding externo para que no toque los bordes de la pantalla
            contentPadding = PaddingValues(16.dp),
            // Espacio automático entre cada tarjeta
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // 3. Iteramos
            items(songsList) { song ->

                // 4. Componente Card (La lógica NO cambia, solo el contenedor)
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