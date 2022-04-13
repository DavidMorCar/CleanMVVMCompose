package com.davidmoreno.cleanmvvmcompose.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.davidmoreno.cleanmvvmcompose.component.Screen
import com.davidmoreno.cleanmvvmcompose.ui.theme.CleanMVVMComposeTheme
import com.davidmoreno.cleanmvvmcompose.view.film.FilmListView
import com.davidmoreno.cleanmvvmcompose.view.album.AlbumListView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/** Only Activity of the application */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalCoroutinesApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {}
        /** creates the composable view */
        setContent {
            CleanMVVMComposeTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val context = this
                    /** Navigation Graph */
                    NavHost(
                        navController = navController,
                        startDestination = Screen.AlbumListScreen.route
                    ) {
                        composable(route = Screen.AlbumListScreen.route) {
                            AlbumListView(navController = navController, context = context)
                        }
                        composable(route = Screen.FilmListScreen.route) {
                            FilmListView(navController = navController, context = context)
                        }
                    }
                }
            }
        }
    }
}
