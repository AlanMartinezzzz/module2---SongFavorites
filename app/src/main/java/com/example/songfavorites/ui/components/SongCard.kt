package com.example.songfavorites.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.songfavorites.data.model.Song

/*
 * SongCard
 * Este es un componente "Stateless" (sin estado interno).

 */
@Composable
fun SongCard(
    song: Song,
    onFavoriteClick: (String) -> Unit // Event Hoisting: notificamos al padre el ID
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Columna para el Texto (Título y Artista)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = song.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = song.artist,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Icono de Corazón (Requisito visual página 48)
            IconButton(onClick = { onFavoriteClick(song.id) }) {
                Icon(
                    imageVector = if (song.isFavorite) {
                        Icons.Default.Favorite // Corazón lleno
                    } else {
                        Icons.Default.FavoriteBorder // Corazón contorno
                    },
                    contentDescription = "Favorite",
                    tint = if (song.isFavorite) Color.Red else Color.Gray
                )
            }
        }
    }
}