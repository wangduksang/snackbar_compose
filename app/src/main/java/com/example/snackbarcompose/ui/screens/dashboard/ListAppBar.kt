package com.example.snackbarcompose.ui.screens.dashboard

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.snackbarcompose.ui.theme.topAppBarBackgroundColor
import com.example.snackbarcompose.ui.theme.topAppBarContentColor
import com.example.snackbarcompose.R

@Composable
fun ListAppBar(
) {
    DefaultListAppBar(
    )
}

@Composable
fun DefaultListAppBar(

) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.list_screen_title),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar(
    )
}
