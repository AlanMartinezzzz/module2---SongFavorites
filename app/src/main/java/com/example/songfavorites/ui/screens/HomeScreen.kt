package com.example.songfavorites.ui.screens

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
 * HomeScreen
 * Esta es la pantalla principal que conecta el ViewModel con la UI.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    // 1. Observamos la lista de canciones desde el ViewModel
    // Usamos 'by' para acceder directamente al valor de mutableStateOf
    val songsList by viewModel.songs

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("StreamUI - My Songs", fontWeight = FontWeight.Bold)
                }
            )
        }
    ) { paddingValues ->

        // 2. Usamos LazyColumn para mostrar una lista eficiente (como un RecyclerView)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {

            // 3. Iteramos sobre la lista de canciones del estado
            items(songsList) { song ->

                // 4. Llamamos a nuestro componente SongCard
                SongCard(
                    song = song,
                    onFavoriteClick = { id ->
                        // EVENTO: Cuando se hace clic, llamamos al ViewModel
                        // Esto es "Event Hoisting" (el evento sube al ViewModel)
                        viewModel.toggleFavorite(id)
                    }
                )
            }
        }
    }
}