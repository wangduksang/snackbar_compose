package com.example.weathercompose.data.repositories

import com.example.weathercompose.data.WeatherDao
import com.example.weathercompose.data.models.Location
import com.example.weathercompose.data.models.Weather
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class WeatherRepository @Inject constructor(private val weatherDao: WeatherDao) {

    val getAllDataMap: Flow<Map<Location, List<Weather>>> = weatherDao.getAllWeatherData()
    suspend fun deleteAllData(){
        weatherDao.deleteWeatherData()
    }
    suspend fun addLocationAndWeatherList(location: Location, weatherList: List<Weather>) =
        weatherDao.addLocationAndWeatherList(location = location, weatherList = weatherList)
}