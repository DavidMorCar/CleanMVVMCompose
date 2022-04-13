package com.davidmoreno.cleanmvvmcompose.domain.repository.album

import com.davidmoreno.cleanmvvmcompose.domain.model.response.Album

/** BaseAlbum repository */
interface BaseAlbumRepository {
    suspend fun getAlbumList(): List<Album>?
}