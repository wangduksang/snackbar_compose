package com.example.snackbarcompose.data

import androidx.room.*
import com.example.snackbarcompose.data.models.Location
import kotlinx.coroutines.flow.Flow
import com.example.snackbarcompose.data.models.Weather

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


//    @Query(
//        "SELECT * FROM location_table JOIN weather_table ON location_table.id = weather_table.location_id " +
//                "ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END"
//    )
//    fun sortByLowPriority(): Flow<Map<Location, List<Weather>>>
//
//    @Query(
//        "SELECT * FROM location_table JOIN weather_table ON location_table.id = weather_table.location_id " +
//                "ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END"
//    )
//    fun sortByHighPriority(): Flow<Map<Location, List<Weather>>>
}