package com.example.snackbarcompose.data.repositories

import com.example.snackbarcompose.data.WeatherDao
import com.example.snackbarcompose.data.models.Location
import com.example.snackbarcompose.data.models.Weather
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class WeatherRepository @Inject constructor(private val weatherDao: WeatherDao) {

    val getAllDataMap: Flow<Map<Location, List<Weather>>> = weatherDao.getAllWeatherData()

//    suspend fun addLocation(location: Location) = weatherDao.addLocation(location = location)
//
//
//    suspend fun addWeather(locationId: Long, weather: Weather) {
//        weather.locationId = locationId
//        weatherDao.addWeather(weather = weather)
//    }

    suspend fun addLocationAndWeatherList(location: Location, weatherList: List<Weather>) =
        weatherDao.addLocationAndWeatherList(location = location, weatherList = weatherList)
}