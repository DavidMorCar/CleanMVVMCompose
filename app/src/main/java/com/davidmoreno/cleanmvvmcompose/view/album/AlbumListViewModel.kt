package com.davidmoreno.cleanmvvmcompose.view.album

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidmoreno.cleanmvvmcompose.domain.usecase.AlbumListUseCase
import com.davidmoreno.cleanmvvmcompose.domain.util.CommonOrder
import com.davidmoreno.cleanmvvmcompose.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/** iTuns albums ViewModel */
@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val albumListUseCase: AlbumListUseCase
) : ViewModel() {
    private val _state = mutableStateOf(AlbumListState())
    val state: State<AlbumListState> = _state

    private var getAlbumListJob: Job? = null

    /** First state of the ViewModel */
    init {
        getAlbumList(CommonOrder.Title(OrderType.Ascending))
    }

    /** Function to call if any event changed */
    fun onEvent(event: AlbumListEvent) {
        when (event) {
            is AlbumListEvent.Order -> {
                if (state.value.albumOrder::class == event.commonOrder::class &&
                    state.value.albumOrder.orderType == event.commonOrder.orderType
                ) {
                    return
                }
                getAlbumList(event.commonOrder)
            }
            else -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    /** Function to make the Album call */
    private fun getAlbumList(commonOrder: CommonOrder) {
        getAlbumListJob?.cancel()
            getAlbumListJob = albumListUseCase.getAlbumList(commonOrder)
                .onEach { albums ->
                    _state.value = state.value.copy(
                        albumList = albums,
                        albumOrder = commonOrder
                    )
                }
                .launchIn(viewModelScope)
    }
}