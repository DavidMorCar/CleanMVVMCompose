package com.davidmoreno.cleanmvvmcompose.domain.repository.album

import com.davidmoreno.cleanmvvmcompose.domain.model.response.Album
import com.davidmoreno.cleanmvvmcompose.service.album.AlbumAPI
import com.davidmoreno.cleanmvvmcompose.service.album.AlbumAPIService

/** Album repository */
class AlbumRepository(
    albumAPI: AlbumAPI
) : BaseAlbumRepository {

    private val albumService: AlbumAPIService = AlbumAPIService(albumAPI)

    override suspend fun getAlbumList(): List<Album>? {
        return albumService.getAlbumList()
    }
}