package com.davidmoreno.cleanmvvmcompose.view.film

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidmoreno.cleanmvvmcompose.domain.usecase.FilmListUseCase
import com.davidmoreno.cleanmvvmcompose.domain.util.CommonOrder
import com.davidmoreno.cleanmvvmcompose.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/** StarWars films ViewModel */
@HiltViewModel
class FilmListViewModel @Inject constructor(
    private val filmListUseCase: FilmListUseCase
) : ViewModel() {
    private val _state = mutableStateOf(FilmListState())
    val state: State<FilmListState> = _state

    private var getFilmListJob: Job? = null

    /** First state of the ViewModel */
    init {
        getFilmList(CommonOrder.Title(OrderType.Ascending))
    }

    /** Function to call if any event changed */
    fun onEvent(event: FilmListEvent) {
        when (event) {
            is FilmListEvent.Order -> {
                if (state.value.filmOrder::class == event.commonOrder::class &&
                    state.value.filmOrder.orderType == event.commonOrder.orderType
                ) {
                    return
                }
                event.commonOrder.orderType.let { CommonOrder.Title(OrderType.Descending) }
                getFilmList(event.commonOrder)
            }
            else -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    /** Function to make the Film call */
    private fun getFilmList(commonOrder: CommonOrder) {
        getFilmListJob?.cancel()
        viewModelScope.launch {
            getFilmListJob = filmListUseCase.getFilmListCase(commonOrder)
                .onEach { films ->
                    _state.value = state.value.copy(
                        filmList = films,
                        filmOrder = commonOrder
                    )
                }
                .launchIn(viewModelScope)
        }
    }
}
