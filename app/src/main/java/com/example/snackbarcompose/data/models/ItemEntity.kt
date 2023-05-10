package com.example.snackbarcompose.data.models

data class Item(
    val title: String = "",
    val imagePath: String = "",
    val descInImage: String = "",
    val hasClock: Boolean = java.lang.Boolean.FALSE,
    val desc: String = "",
    val rate: Float = 0.0F,
    val distance: String = ""
)
