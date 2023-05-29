package com.example.weathercompose.data.models

data class LocationWeather (
    var name: String = "",
    var longitude: Double = 0.0,
    var latitude: Double = 0.0,
    var locationId: Long = 0,
    var tempMin: Double = 0.0,
    var tempMax: Double = 0.0,
    var state: String = "",
    var desc: String = "",
    var dt: Int = 0,
    var dtTxt: String = "",
)