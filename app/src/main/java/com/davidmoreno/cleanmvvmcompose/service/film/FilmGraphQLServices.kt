package com.davidmoreno.cleanmvvmcompose.service.film

import com.apollographql.apollo3.ApolloClient
import com.davidmoreno.cleanmvvmcompose.FilmQuery
import com.davidmoreno.cleanmvvmcompose.domain.util.APOLLO_BASE_URL

/** Class for Films graphQL calls */
class FilmGraphQLServices {

    /** Creates an Apollo Client */
    private val apolloClient = ApolloClient.Builder()
        .serverUrl(APOLLO_BASE_URL)
        .build()

    /** Function to load a FilmList */
    suspend fun getFilms(): List<FilmQuery.Film?>? {
        // Execute your query. This will suspend until the response is received.
        val response = apolloClient.query(FilmQuery()).execute()
        return response.data?.allFilms?.films
    }
}