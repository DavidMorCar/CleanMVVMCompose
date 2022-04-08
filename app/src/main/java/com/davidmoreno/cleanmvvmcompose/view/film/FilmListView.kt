package com.davidmoreno.cleanmvvmcompose.view.film

import android.app.Activity
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.davidmoreno.cleanmvvmcompose.component.FilmItem
import com.davidmoreno.cleanmvvmcompose.component.OrderSection
import com.davidmoreno.cleanmvvmcompose.domain.util.openURL
import kotlinx.coroutines.ExperimentalCoroutinesApi

/** FilmList composable view */
@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@Composable
fun FilmListView(
    navController: NavController,
    viewModel: FilmListViewModel = hiltViewModel(),
    context: Activity
) {
    val state = viewModel.state.value

    Scaffold(
        floatingActionButton = {
            Column(
            ) {
                FloatingActionButton(
                    onClick = {
                        openURL(
                            "https://www.linkedin.com/in/david-moreno-533bb5155/",
                            context
                        )
                    },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.DeveloperMode,
                        contentDescription = "Developer Info"
                    )
                }
            }
        },

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Star Wars Movies",
                    style = MaterialTheme.typography.h5
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(FilmListEvent.ToggleOrderSection)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = "Sort"
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    order = state.filmOrder,
                    isPriceVisible = false,
                    onOrderChange = {
                        viewModel.onEvent(FilmListEvent.Order(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.filmList!!) { film ->
                    FilmItem(
                        film = film!!,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                            },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}