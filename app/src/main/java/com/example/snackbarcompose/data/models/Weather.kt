package com.example.snackbarcompose.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.snackbarcompose.util.Constants.WEATHER_TABLE

@Entity(tableName = WEATHER_TABLE)
data class Weather(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "location_id")
    val locationId: String = "",
    @ColumnInfo(name = "lon")
    val longitude: Float = 0.0F,
    @ColumnInfo(name = "lat")
    val latitude: Float = 0.0F,
    @ColumnInfo(name = "description")
    val description: String = "",
    @ColumnInfo(name = "temp_min")
    val tempMin: Float = 0.0F,
    @ColumnInfo(name = "temp_max")
    val tempMax: Float = 0.0F,
    @ColumnInfo(name = "dt")
    val dt: String = "",
)
