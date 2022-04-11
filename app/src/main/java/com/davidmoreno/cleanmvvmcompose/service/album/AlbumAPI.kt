package com.davidmoreno.cleanmvvmcompose.service.album

import com.davidmoreno.cleanmvvmcompose.domain.model.response.AlbumResponse
import retrofit2.Response
import retrofit2.http.GET

/** Retrofit interface */
interface AlbumAPI {

    @GET("topalbums/limit=100/json")
    suspend fun getAlbumService(): Response<AlbumResponse>
}