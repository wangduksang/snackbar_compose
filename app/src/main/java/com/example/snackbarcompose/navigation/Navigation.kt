package com.example.snackbarcompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.snackbarcompose.navigation.destinations.dashboardComposable
import com.example.snackbarcompose.ui.viewmodels.SharedViewModel
import com.example.snackbarcompose.util.Constants.DASHBOARD_SCREEN
import com.google.accompanist.navigation.animation.AnimatedNavHost

@ExperimentalAnimationApi
@Composable
fun SetupNavigation(
    navController: NavHostController
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

//    AnimatedNavHost(
//        navController = navController,
//        startDestination = DASHBOARD_SCREEN
//    ) {
//        dashboardComposable(
//            navigateToTaskScreen = screen.list,
//        )
//    }
}