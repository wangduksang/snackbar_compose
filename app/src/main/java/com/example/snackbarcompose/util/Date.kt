package com.example.snackbarcompose.util

import java.text.SimpleDateFormat
import java.util.Locale


//private val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.ENGLISH)

private val simpleDateOnlyDayFormat = SimpleDateFormat("dd", Locale.ENGLISH)
fun getDateString(time: Long) : String = simpleDateOnlyDayFormat.format(time * 1000L)

fun getDateString(time: Int) : String = simpleDateOnlyDayFormat.format(time * 1000L)
