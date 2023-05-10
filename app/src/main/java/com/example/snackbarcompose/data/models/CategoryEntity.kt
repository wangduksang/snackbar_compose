package com.example.snackbarcompose.data.models
data class Category(
    val title: String = "",
    val itemList: ArrayList<Item> = ArrayList(),
    val imageArrange: ImageArrange = ImageArrange.NONE
)