package com.example.snackbarcompose.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.abs

val simpleMMDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)

private val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)

private val simpleDateOnlyDayFormat = SimpleDateFormat("dd", Locale.ENGLISH)

fun getDateString(time: Int): String = simpleDateOnlyDayFormat.format(time * 1000L)

fun convertDateStandardSequence(data: String): String = simpleDateFormat.parse(data).toString()

fun getCelcius(fahrenheit: Double): Double = fahrenheit - 273

