package com.example.snackbarcompose.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.abs


//private val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.ENGLISH)

private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
private val simpleMMDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)

private val simpleDateOnlyDayFormat = SimpleDateFormat("dd", Locale.ENGLISH)

fun getDateString(time: Long): String = simpleDateOnlyDayFormat.format(time * 1000L)

fun getDateString(time: Int): String = simpleDateOnlyDayFormat.format(time * 1000L)

fun getMonthString(time: Int): String = simpleMMDateFormat.format(time * 1000L)

fun getMonthName(date: String): String {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val dateStr = format.parse(date)
    val dateItem = dateStr.toString().split(" ")
    dateItem?.let {
        println("month => ${dateItem[1]}")
    }
    return dateItem[1]
}

fun getDayName(date: String): String {

    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val dateStr = format.parse(date)
    val dateItem = dateStr.toString().split(" ")
    dateItem?.let {
        println("month => ${dateItem[2]}")
    }
    return dateItem[2]
}



//fun getCelcius(fahrenheit : Double) : Double =  5f / 9 * (fahrenheit - 32)
fun getCelcius(fahrenheit: Double): Double = fahrenheit - 273

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