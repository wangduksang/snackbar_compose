package com.example.snackbarcompose.data.models

data class Item(
    val id: Int = 0,
    val itemsId: Int = 0,
    val priority: String = "",
    val desc: String = "",
    val summary: String = "",
    val rate: Float = 0.0F,
    val distance: String = "",
    val imgUrl: String = ""
)