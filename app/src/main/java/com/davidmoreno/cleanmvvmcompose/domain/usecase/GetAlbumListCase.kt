package com.davidmoreno.cleanmvvmcompose.domain.usecase

import com.davidmoreno.cleanmvvmcompose.domain.model.response.Album
import com.davidmoreno.cleanmvvmcompose.domain.repository.album.BaseAlbumRepository
import com.davidmoreno.cleanmvvmcompose.domain.util.CommonOrder
import com.davidmoreno.cleanmvvmcompose.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/** Class to load and sort the SongList */
class GetAlbumListCase(
    private val repository: BaseAlbumRepository
) {

    operator fun invoke(
        songOrder: CommonOrder = CommonOrder.Title(orderType = OrderType.Descending)
    ): Flow<List<Album>> {
        return flow { emit(repository.getSongList()) }.map { notes ->
            when (songOrder.orderType) {
                is OrderType.Ascending -> {
                    when (songOrder) {
                        is CommonOrder.Title -> notes!!.sortedBy { it.title.label.lowercase() }
                        is CommonOrder.Price -> notes!!.sortedBy {
                            it.price.label.replace("$", "").toDouble()
                        }
                    }
                }
                is OrderType.Descending -> {
                    when (songOrder) {
                        is CommonOrder.Title -> notes!!.sortedByDescending {
                            it.title.label.lowercase()
                        }
                        is CommonOrder.Price -> notes!!.sortedByDescending {
                            it.price.label.replace("$", "").toDouble()
                        }
                    }
                }
            }
        }!!
    }
}