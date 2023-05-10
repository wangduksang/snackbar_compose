package com.example.snackbarcompose.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.snackbarcompose.ui.viewmodels.SharedViewModel
import com.example.snackbarcompose.util.Constants.DASHBOARD_SCREEN
import com.example.snackbarcompose.util.Constants.ARGUMENT_KEY
import com.example.snackbarcompose.util.toAction

@ExperimentalAnimationApi
fun NavGraphBuilder.dashboardComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
) {
    composable(
        route = DASHBOARD_SCREEN,
        arguments = listOf(navArgument(ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(ARGUMENT_KEY).toAction()

        // to screen
//        LaunchedEffect(key1 = action) {
//            sharedViewModel.action.value = action
//        }

        //DashboardScreen
    }
}