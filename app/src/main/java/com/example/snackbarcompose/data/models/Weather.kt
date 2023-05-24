package com.example.snackbarcompose.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.snackbarcompose.util.Constants.WEATHER_TABLE

@Entity(
    tableName = WEATHER_TABLE,
    foreignKeys = [ForeignKey(
        entity = Location::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("location_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Weather(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "location_id")
    val locationId: String = "",
    @ColumnInfo(name = "description")
    val description: String = "",
    @ColumnInfo(name = "temp_min")
    var tempMin: Float = 0.0F,
    @ColumnInfo(name = "temp_max")
    var tempMax: Float = 0.0F,
    @ColumnInfo(name = "dt")
    var dt: String = "",
)
