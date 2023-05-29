package com.example.weathercompose.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weathercompose.util.Constants.LOCATION_TABLE

@Entity(tableName = LOCATION_TABLE)
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "lon")
    var longitude: Double = 0.0,
    @ColumnInfo(name = "lat")
    var latitude: Double = 0.0,
)
