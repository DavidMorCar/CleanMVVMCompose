package com.davidmoreno.cleanmvvmcompose.domain.repository.album

import com.davidmoreno.cleanmvvmcompose.domain.model.request.Album
import com.davidmoreno.cleanmvvmcompose.service.album.AlbumAPI
import com.davidmoreno.cleanmvvmcompose.service.album.AlbumAPIService

/** Album repository */
class AlbumRepository(
    albumAPI: AlbumAPI
) : BaseAlbumRepository {

    private val albumService: AlbumAPIService = AlbumAPIService(albumAPI)

    override suspend fun getSongList(): List<Album>? {
        return albumService.getSongList()
    }
}