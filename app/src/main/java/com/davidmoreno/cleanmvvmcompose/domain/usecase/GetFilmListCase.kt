package com.davidmoreno.cleanmvvmcompose.domain.usecase

import com.davidmoreno.cleanmvvmcompose.FilmQuery
import com.davidmoreno.cleanmvvmcompose.domain.repository.film.BaseFilmRepository
import com.davidmoreno.cleanmvvmcompose.domain.util.CommonOrder
import com.davidmoreno.cleanmvvmcompose.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/** Class to load and sort the FilmList */
class GetFilmListCase(
    private val repository: BaseFilmRepository
) {

    suspend operator fun invoke(
        filmOrder: CommonOrder = CommonOrder.Title(orderType = OrderType.Descending)
    ): Flow<List<FilmQuery.Film?>?> {
        return flow {
            emit(repository.getFilmList())
        }.map { films ->
            when (filmOrder.orderType) {
                is OrderType.Ascending -> {
                    when (filmOrder) {
                        is CommonOrder.Title -> films?.sortedBy { it?.title }
                        else -> films?.sortedBy { it?.title }
                    }
                }
                is OrderType.Descending -> {
                    when (filmOrder) {
                        is CommonOrder.Title -> films?.sortedByDescending { it?.title }
                        else -> films?.sortedByDescending { it?.title }
                    }
                }
            }
        }
    }
}