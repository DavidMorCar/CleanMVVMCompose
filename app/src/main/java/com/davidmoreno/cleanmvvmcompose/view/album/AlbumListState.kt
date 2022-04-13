package com.davidmoreno.cleanmvvmcompose.view.album

import com.davidmoreno.cleanmvvmcompose.domain.model.response.Album
import com.davidmoreno.cleanmvvmcompose.domain.util.CommonOrder
import com.davidmoreno.cleanmvvmcompose.domain.util.OrderType

/** AlbumList state class */
data class AlbumListState(
    val albumList: List<Album> = emptyList(),
    val albumOrder: CommonOrder = CommonOrder.Title(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
