package com.example.weathercompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weathercompose.data.models.Location
import com.example.weathercompose.data.models.Weather

@Database(
    entities = [
        Weather::class,
        Location::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {

    // specify dao
    abstract fun weatherDao(): WeatherDao
}