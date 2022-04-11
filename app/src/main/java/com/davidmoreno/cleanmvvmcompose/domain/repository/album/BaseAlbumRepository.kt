package com.davidmoreno.cleanmvvmcompose.domain.repository.album

import com.davidmoreno.cleanmvvmcompose.domain.model.response.Album

/** BaseSong repository */
interface BaseAlbumRepository {
    suspend fun getSongList(): List<Album>?
}