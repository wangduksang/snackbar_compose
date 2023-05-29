package com.example.weathercompose.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.weathercompose.util.Constants.WEATHER_TABLE
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

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
    var locationId: Long = 0,
    @ColumnInfo(name = "temp_min")
    var tempMin: Double = 0.0,
    @ColumnInfo(name = "temp_max")
    var tempMax: Double = 0.0,
    @ColumnInfo(name = "state")
    var state: String = "",
    @ColumnInfo(name = "desc")
    var desc: String = "",
    @ColumnInfo(name = "dt")
    var dt: Int = 0,
    @ColumnInfo(name = "dtTxt")
    var dtTxt: String = "",
)



