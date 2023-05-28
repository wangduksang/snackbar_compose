package com.example.snackbarcompose.data.models

import androidx.room.ColumnInfo

data class LocationWeather (
    var name: String = "",
    var longitude: Double = 0.0,
    var latitude: Double = 0.0,
    var locationId: Long = 0,
    var tempMin: Double = 0.0,
    var tempMax: Double = 0.0,
    var state: String = "",
    var dt: String = "",
)