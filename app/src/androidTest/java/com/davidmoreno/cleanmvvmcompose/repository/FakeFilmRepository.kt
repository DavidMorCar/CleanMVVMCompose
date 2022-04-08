package com.davidmoreno.cleanmvvmcompose.repository

import com.davidmoreno.cleanmvvmcompose.FilmQuery
import com.davidmoreno.cleanmvvmcompose.domain.repository.film.BaseFilmRepository

/** Fake FilmRepository */
class FakeFilmRepository() : BaseFilmRepository {

    /** Function that call the films list */
    override suspend fun getFilmList(): List<FilmQuery.Film?>? = getFilmTestArray()

    /** Function to mock a Film List */
    fun getFilmTestArray(): List<FilmQuery.Film?>? = listOf(
        FilmQuery.Film
            ("Test", "DirectorTest", "", null),
        FilmQuery.Film
            ("Test2", "DirectorTest2", "", null),
        FilmQuery.Film
            ("Test3", "DirectorTest3", "", null)
    )

}