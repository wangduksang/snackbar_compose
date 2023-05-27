package com.example.snackbarcompose.data.models.constance

import com.example.snackbarcompose.R

enum class WeatherDecorType(val word: String, val icon: Int) {
//sealed class WeatherDecorType(value : String) {
//    abstract fun getImage() : Int
//
//    class None(value : String) : WeatherDecorType(value) {
//        override fun getImage(): Int {
//            return R.drawable.ic_wi_na
//        }
//    }
//
//    class LightRain(value : String) : WeatherDecorType(value) {
//        override fun getImage(): Int {
//            return R.drawable.ic_wi_showers
//        }
//    }

    NONE("", R.drawable.ic_wi_na),
    LIGHT_RAIN("light rain", R.drawable.ic_wi_showers),
    BROKEN_CLOUDS("broken clouds", R.drawable.ic_wi_day_cloudy_high),
    SCATTERED_CLOUDS("scattered clouds", R.drawable.ic_wi_day_cloudy_high),
    OVERCAST_CLOUDS("overcast_clouds", R.drawable.ic_wi_day_cloudy_high),
    FEW_CLOUDS("few clouds", R.drawable.ic_wi_day_cloudy_high),
    CLEAR_SKY("clear sky,", R.drawable.ic_wi_day_sunny),
}
