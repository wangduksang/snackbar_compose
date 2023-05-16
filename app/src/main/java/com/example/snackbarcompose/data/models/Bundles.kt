package com.example.snackbarcompose.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.snackbarcompose.util.Constants.BUNDLES_TABLE

@Entity(tableName = BUNDLES_TABLE)
data class Bundles(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "bg_img")
    val bgImg: String = "",
    @ColumnInfo(name = "priority")
    val priority: String = "",
    @ColumnInfo(name = "type")
    val type: String = "",
)
