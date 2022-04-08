package com.davidmoreno.cleanmvvmcompose.view.film

import com.davidmoreno.cleanmvvmcompose.domain.util.CommonOrder

/** FilmList event class */
sealed class FilmListEvent {
    data class Order(val commonOrder: CommonOrder) : FilmListEvent()
    object ToggleOrderSection : FilmListEvent()
}