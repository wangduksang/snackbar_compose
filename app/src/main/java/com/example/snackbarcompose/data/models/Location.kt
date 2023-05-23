package com.example.snackbarcompose.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.snackbarcompose.util.Constants.LOCATION_TABLE

@Entity(tableName = LOCATION_TABLE)
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val location: String = "",
    val priority: Priority,
)
