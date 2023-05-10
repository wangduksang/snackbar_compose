package com.example.snackbarcompose.ui.screens.dashboard

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.example.snackbarcompose.data.models.Category
import com.example.snackbarcompose.util.RequestState

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ListContent(
    allTasks: RequestState<List<Category>>,
    searchedTasks: RequestState<List<Category>>,
    lowPriorityTasks: List<Category>,
    highPriorityTasks: List<Category>,
    sortState: RequestState<Category>,
) {

}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun HandleListContent(
    tasks: List<Category>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {

    EmptyContent()
}








