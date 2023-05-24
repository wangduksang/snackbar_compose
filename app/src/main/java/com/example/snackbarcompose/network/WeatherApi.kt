package com.example.snackbarcompose.network

import com.example.snackbarcompose.data.remote.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast?")
    suspend fun getWeather(
        @Query("lon") longitude:String,
        @Query("lat") latitude: String,
        @Query("appid") appId: String
    ): WeatherResponse
}