package com.example.snackbarcompose.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.snackbarcompose.util.Constants

@Entity(tableName = Constants.ITEM_TABLE)
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "item_id")
    val itemsId: Int = 0,
    @ColumnInfo(name = "priority")
    val priority: String = "",
    @ColumnInfo(name = "desc")
    val desc: String = "",
    @ColumnInfo(name = "summary")
    val summary: String = "",
    @ColumnInfo(name = "rate")
    val rate: Float = 0.0F,
    @ColumnInfo(name = "distance")
    val distance: String = "",
    @ColumnInfo(name = "imgUrl")
    val imgUrl: String = ""
)