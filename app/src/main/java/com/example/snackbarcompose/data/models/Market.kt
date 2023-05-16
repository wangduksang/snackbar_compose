package com.example.snackbarcompose.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.snackbarcompose.util.Constants

@Entity(tableName = Constants.MARKET_TABLE)
data class Market(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "bg_img")
    val bgImg: String = "",
    @ColumnInfo(name = "loc")
    val loc: String = "",
    @ColumnInfo(name = "loc_img_url")
    val locImgUrl: String = "",
)
