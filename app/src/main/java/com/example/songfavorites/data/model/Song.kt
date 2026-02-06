package com.example.songfavorites.data.model

/**
 * Song
 * Este archivo define el MODELO DE DATOS de nuestra aplicación.
 *
 * En arquitectura MVVM + UDF:
 * - El modelo representa el "estado base" de la app.
 * - NO contiene lógica de UI.
 * - NO contiene lógica de negocio.
 * - Solo describe cómo es un objeto Song.
 *
 * lo que debemos de hacer es que debemos indicar y manejar una lista de canciones
 * y poder marcarlas cada una como favorita.
 */

/**
 * data class
 * ----------
 * Usamos "data class" porque:
 * - Kotlin genera automáticamente:
 *   - equals()
 *   - hashCode()
 *   - toString()
 *   - copy()
 *
 * Esto es CLAVE para UDF, porque:
 * - El estado es inmutable
 * - Para cambiar algo, usamos copy()
 */
data class Song(

    /**
     * id
     * Este va ser nuestro identificador único de la canción.
     * Nos va a servir para saber si se presiono la cancion y ccomparar canciones
     */
    val id: Int,
    /**
     * titulo del nombre de la canción que se mostrará en la UI.
     */
    val title: String,

    /**
     * artist
     * ------
     * Nombre del artista o banda.
     */
    val artist: String,

    /**
     * isFavorite para Indicar si la canción está marcada como favorita.
     *
     * IMPORTANTE:
     * - Tiene un valor por defecto (false)
     * - Esto permite crear canciones no favoritas sin especificarlo
     *
     * TEORIA : En UDF
     * - El ViewModel NO modifica este valor directamente
     * - Se crea una NUEVA copia con copy(isFavorite = ...)
     */
    val isFavorite: Boolean = false
)
