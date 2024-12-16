package com.snap_fetch_kmp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.snap_fetch_kmp.view.screens.PhotoDetailsScreen
import com.snap_fetch_kmp.view.screens.PhotosScreen
import com.snap_fetch_kmp.view.screens.SplashScreen


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Screen.Splash
    ) {
        composable<Screen.Splash> {
            SplashScreen()
        }
        composable<Screen.Photos> {
            PhotosScreen()
        }

        composable<Screen.PhotoDetails> { backStackEntry ->
            val args = backStackEntry.toRoute<Screen.PhotoDetails>()
            PhotoDetailsScreen(photoId = args.photoId)
        }
    }
}

