package com.davidmoreno.cleanmvvmcompose

import com.davidmoreno.cleanmvvmcompose.domain.usecase.FilmListUseCase
import com.davidmoreno.cleanmvvmcompose.domain.usecase.GetFilmListCase
import com.davidmoreno.cleanmvvmcompose.domain.util.OrderType
import com.davidmoreno.cleanmvvmcompose.repository.FakeFilmRepository
import com.davidmoreno.cleanmvvmcompose.view.film.FilmListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/** Class to test the FilmListViewModel */
@ExperimentalCoroutinesApi
class FilmListViewModelTest {

    private lateinit var viewModel: FilmListViewModel
    private var fakeFilmRepository: FakeFilmRepository = FakeFilmRepository()

    /** Setup the fake data */
    @Before
    fun setup() {
        viewModel = FilmListViewModel(FilmListUseCase(GetFilmListCase(fakeFilmRepository)))
    }

    /** FilmViewModel init states test */
    @Test
    fun testFilmInitViewModelStates() {
        Assert.assertFalse(viewModel.state.value.isOrderSectionVisible)
        Assert.assertNotNull(viewModel.state.value.filmList)
        Assert.assertEquals(OrderType.Descending, viewModel.state.value.filmOrder.orderType)
    }
}













