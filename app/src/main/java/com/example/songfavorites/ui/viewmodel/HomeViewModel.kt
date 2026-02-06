package com.example.songfavorites.ui.viewmodel


// Importamos ViewModel, que es la clase base del patrón MVVM
import androidx.lifecycle.ViewModel

// Importamos mutableStateOf para manejar estado observable en Compose
import androidx.compose.runtime.mutableStateOf

// Importamos la clase Song (nuestro modelo de datos)
import com.example.songfavorites.data.model.Song

/*
 * HomeViewModel
 * maneja el estado de la pantalla principal
 */
class HomeViewModel : ViewModel() {

    /*
     * Lista de canciones
     * - mutableStateOf permite que Compose "escuche" cambios
     * - Cuando esta lista cambie, la UI se recompondrá automáticamente
     */
    val songs = mutableStateOf( // para que se actuialice la UI
        listOf(
            // Creamos canciones de ejemplo (datos estáticos)
            Song(
                id = "1",
                title = "Instant Crush ",
                artist = "Daft Punk",
                isFavorite = false
            ),
            Song(
                id = "2",
                title = "Stars",
                artist = "Shaya Zamora",
                isFavorite = false
            ),
            Song(
                id = "3",
                title = "LOVER",
                artist = "Sondae",
                isFavorite = false
            )
        )
    )

    /*
     * Función para marcar o desmarcar una canción como favorita.
     * Recibe el ID de la canción que queremos cambiar.
     */
    fun toggleFavorite(songId: String) {

        // En UDF, no modificamos la lista, creamos una nueva con los cambios
        songs.value = songs.value.map { song ->

            // Si el ID coincide, invertimos el estado isFavorite
            if (song.id == songId) {
                // Usamos .copy() para mantener la inmutabilidad
                song.copy(isFavorite = !song.isFavorite)
            } else {
                // Si no coincide, dejamos la canción igual
                song
            }
        }
    }
}
