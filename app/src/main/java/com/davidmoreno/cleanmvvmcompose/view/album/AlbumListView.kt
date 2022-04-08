package com.davidmoreno.cleanmvvmcompose.view.album

import android.app.Activity
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.davidmoreno.cleanmvvmcompose.component.OrderSection
import com.davidmoreno.cleanmvvmcompose.component.Screen
import com.davidmoreno.cleanmvvmcompose.component.SongItem
import com.davidmoreno.cleanmvvmcompose.domain.util.openURL
import kotlinx.coroutines.ExperimentalCoroutinesApi

/** SongList composable view */
@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@Composable
fun SongListView(
    navController: NavController,
    viewModel: AlbumListViewModel = hiltViewModel(),
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
                Spacer(modifier = Modifier.height(8.dp))
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.FilmListScreen.route)
                    },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.AirplanemodeActive,
                        contentDescription = "GraphQL Test"
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
                    text = "iTuns Top Albums",
                    style = MaterialTheme.typography.h5
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(AlbumListEvent.ToggleOrderSection)
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
                    order = state.songOrder,
                    isPriceVisible = true,
                    onOrderChange = {
                        viewModel.onEvent(AlbumListEvent.Order(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.albumList) { song ->
                    SongItem(
                        album = song,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                openURL(song.url.label, context)
                            },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}