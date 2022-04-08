package com.davidmoreno.cleanmvvmcompose.domain.repository.film

import com.davidmoreno.cleanmvvmcompose.FilmQuery

/** BaseFilm repository */
interface BaseFilmRepository {
    suspend fun getFilmList(): List<FilmQuery.Film?>?
}