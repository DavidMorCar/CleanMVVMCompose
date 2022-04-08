package com.davidmoreno.cleanmvvmcompose.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.davidmoreno.cleanmvvmcompose.domain.util.CommonOrder
import com.davidmoreno.cleanmvvmcompose.domain.util.OrderType

/** Generic OrderSection composable view */
@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    order: CommonOrder = CommonOrder.Title(OrderType.Descending),
    isPriceVisible: Boolean,
    onOrderChange: (CommonOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Title",
                selected = order is CommonOrder.Title,
                onSelect = { onOrderChange(CommonOrder.Title(order.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            if (isPriceVisible) {
                DefaultRadioButton(
                    text = "Price",
                    selected = order is CommonOrder.Price,
                    onSelect = { onOrderChange(CommonOrder.Price(order.orderType)) }
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = order.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(order.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = order.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(order.copy(OrderType.Descending))
                }
            )
        }
    }
}