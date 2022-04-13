package com.davidmoreno.cleanmvvmcompose.domain.usecase

import com.davidmoreno.cleanmvvmcompose.domain.model.response.Album
import com.davidmoreno.cleanmvvmcompose.domain.repository.album.BaseAlbumRepository
import com.davidmoreno.cleanmvvmcompose.domain.util.CommonOrder
import com.davidmoreno.cleanmvvmcompose.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/** Class to load and sort the AlbumList */
class GetAlbumListCase(
    private val repository: BaseAlbumRepository
) {

    operator fun invoke(
        albumOrder: CommonOrder = CommonOrder.Title(orderType = OrderType.Descending)
    ): Flow<List<Album>> {
        return flow { emit(repository.getAlbumList()) }.map { albums ->
            when (albumOrder.orderType) {
                is OrderType.Ascending -> {
                    when (albumOrder) {
                        is CommonOrder.Title -> albums!!.sortedBy { it.title.label.lowercase() }
                        is CommonOrder.Price -> albums!!.sortedBy {
                            it.price.label.replace("$", "").toDouble()
                        }
                    }
                }
                is OrderType.Descending -> {
                    when (albumOrder) {
                        is CommonOrder.Title -> albums!!.sortedByDescending {
                            it.title.label.lowercase()
                        }
                        is CommonOrder.Price -> albums!!.sortedByDescending {
                            it.price.label.replace("$", "").toDouble()
                        }
                    }
                }
            }
        }!!
    }
}