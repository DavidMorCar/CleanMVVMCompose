package com.davidmoreno.cleanmvvmcompose.view.album

import com.davidmoreno.cleanmvvmcompose.domain.model.request.Album
import com.davidmoreno.cleanmvvmcompose.domain.util.CommonOrder
import com.davidmoreno.cleanmvvmcompose.domain.util.OrderType

/** SongList state class */
data class AlbumListState(
    val albumList: List<Album> = emptyList(),
    val songOrder: CommonOrder = CommonOrder.Title(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
