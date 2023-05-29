package com.example.weathercompose.util

import java.text.SimpleDateFormat
import java.util.Locale

private val simpleDateOnlyDayFormat = SimpleDateFormat("dd", Locale.ENGLISH)

fun getDateString(time: Int): String = simpleDateOnlyDayFormat.format(time * 1000L)

fun getCelcius(fahrenheit: Double): Double = fahrenheit - 273

