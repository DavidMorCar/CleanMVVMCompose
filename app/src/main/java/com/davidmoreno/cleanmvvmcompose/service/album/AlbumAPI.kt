package com.davidmoreno.cleanmvvmcompose.service.album

import com.davidmoreno.cleanmvvmcompose.domain.model.request.AlbumResponse
import retrofit2.Response
import retrofit2.http.GET

/** Retrofit interface */
interface AlbumAPI {

    @GET("topalbums/limit=100/json")
    suspend fun getComicsService(): Response<AlbumResponse>
}