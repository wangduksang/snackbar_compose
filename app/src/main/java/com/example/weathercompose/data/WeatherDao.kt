package com.example.weathercompose.data

import androidx.room.*
import com.example.weathercompose.data.models.Location
import kotlinx.coroutines.flow.Flow
import com.example.weathercompose.data.models.Weather

@Dao
interface WeatherDao {

    @Transaction
    @Query("SELECT * FROM location_table JOIN weather_table ON location_table.id = weather_table.location_id")
    fun getAllWeatherData(): Flow<Map<Location, List<Weather>>>

    @Transaction
    suspend fun addLocationAndWeatherList(location: Location, weatherList: List<Weather>) {
        deleteWeatherData()
        val locationId = addLocation(location)
        weatherList.forEach { weather ->
            weather.locationId = locationId
            addWeather(weather)
        }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLocation(location: Location): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWeather(weather: Weather)

    @Transaction
    @Delete
    suspend fun deleteWeatherData(){
        deleteWeather()
        deleteLocation()
    }

    @Query("DELETE FROM location_table")
    suspend fun deleteLocation()

    @Query("DELETE FROM weather_table")
    suspend fun deleteWeather()
}