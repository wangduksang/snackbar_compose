package com.example.snackbarcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.snackbarcompose.data.models.Location
import com.example.snackbarcompose.data.models.Weather

@Database(
    entities = [
        Weather::class,
        Location::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class SnackBarDatabase : RoomDatabase() {

    // specify dao
    abstract fun weatherDao(): WeatherDao
}