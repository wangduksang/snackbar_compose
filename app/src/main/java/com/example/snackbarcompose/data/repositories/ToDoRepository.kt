package com.example.snackbarcompose.data.repositories

import com.example.snackbarcompose.data.WeatherDao
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ToDoRepository @Inject constructor(private val weatherDao: WeatherDao) {

}