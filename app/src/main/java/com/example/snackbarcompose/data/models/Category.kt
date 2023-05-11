package com.example.snackbarcompose.data.models

import com.example.snackbarcompose.data.models.constance.ItemDecorType
import com.example.snackbarcompose.data.models.constance.Priority

data class Category(
    val id: Int = 0,
    val title: String = "",
    val priority: Priority,
    val decorType: Int = ItemDecorType.IMAGE_LIST_TOP_3.type, // check effective kotlin
    //var itemList: ArrayList<Item>
    val itemList: ArrayList<Item> = ArrayList(),
)



