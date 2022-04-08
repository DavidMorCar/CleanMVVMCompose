package com.davidmoreno.cleanmvvmcompose.service.album

import com.davidmoreno.cleanmvvmcompose.domain.model.request.Album

/** Class for Album API calls */
class AlbumAPIService constructor(
    private var service: AlbumAPI
) {

    /** Function to load a SongList */
    suspend fun getSongList(): List<Album>? {
        val call = service.getComicsService()
        val response = call.body()
        return if (call.isSuccessful && response != null) {
            response.feedResponse.albumList
        } else {
            null
        }
    }
}