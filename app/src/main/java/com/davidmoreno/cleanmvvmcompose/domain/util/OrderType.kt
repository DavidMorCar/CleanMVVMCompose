package com.davidmoreno.cleanmvvmcompose.domain.util

/** Class with order types */
sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
