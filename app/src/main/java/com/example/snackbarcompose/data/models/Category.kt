package com.example.snackbarcompose.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.snackbarcompose.data.models.constance.ItemDecorType
import com.example.snackbarcompose.data.models.constance.Priority
import com.example.snackbarcompose.util.Constants

@Entity(tableName = Constants.CATEGORY_TABLE)
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "priority")
    val priority: Priority,
    @ColumnInfo(name = "decor_type")
    val decorType: Int = ItemDecorType.IMAGE_LIST_TOP_3.type, // check effective kotlin
)



