package com.davidmoreno.cleanmvvmcompose.component

/** Screen navigation helper */
sealed class Screen(val route: String) {
    object SongListScreen : Screen("song_list_screen")
    object FilmListScreen : Screen("film_list_screen")
}
