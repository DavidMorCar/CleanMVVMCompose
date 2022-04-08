package com.davidmoreno.cleanmvvmcompose.domain.util

/** Class with order variables */
sealed class CommonOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : CommonOrder(orderType)
    class Price(orderType: OrderType) : CommonOrder(orderType)

    fun copy(orderType: OrderType): CommonOrder {
        return when (this) {
            is Title -> Title(orderType)
            is Price -> Price(orderType)
            else -> Title(orderType)
        }
    }
}

