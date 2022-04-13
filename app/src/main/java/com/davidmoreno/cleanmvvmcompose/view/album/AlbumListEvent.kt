package com.davidmoreno.cleanmvvmcompose.view.album

import com.davidmoreno.cleanmvvmcompose.domain.util.CommonOrder

/** AlbumList events to tie  */
sealed class AlbumListEvent {
    data class Order(val commonOrder: CommonOrder) : AlbumListEvent()
    object ToggleOrderSection : AlbumListEvent()
}