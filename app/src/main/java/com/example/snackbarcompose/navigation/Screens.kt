package com.example.snackbarcompose.navigation

import androidx.navigation.NavHostController
import com.example.snackbarcompose.util.Action
import com.example.snackbarcompose.util.Constants.DASHBOARD_SCREEN

class Screens(navController: NavHostController) {
    val list: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task/$taskId")
    }
    val task: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action}") {

            popUpTo(DASHBOARD_SCREEN) { inclusive = true }
        }
    }
}