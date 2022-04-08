package com.davidmoreno.cleanmvvmcompose.domain.repository.film

import com.davidmoreno.cleanmvvmcompose.FilmQuery
import com.davidmoreno.cleanmvvmcompose.service.film.FilmGraphQLServices

/** Film repository */
class FilmRepository() : BaseFilmRepository {

    private val filmGraphQLServices: FilmGraphQLServices = FilmGraphQLServices()

    override suspend fun getFilmList(): List<FilmQuery.Film?>? = filmGraphQLServices.getFilms()
}