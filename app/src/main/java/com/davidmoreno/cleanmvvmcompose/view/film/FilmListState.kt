package com.davidmoreno.cleanmvvmcompose.view.film

import com.davidmoreno.cleanmvvmcompose.FilmQuery
import com.davidmoreno.cleanmvvmcompose.domain.util.CommonOrder
import com.davidmoreno.cleanmvvmcompose.domain.util.OrderType

/** FilmList state class */
data class FilmListState(
    val filmList: List<FilmQuery.Film?>? = emptyList(),
    val filmOrder: CommonOrder = CommonOrder.Title(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)