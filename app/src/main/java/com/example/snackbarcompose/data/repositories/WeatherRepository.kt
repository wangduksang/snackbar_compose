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

//    val sortByLowPriority: Flow<List<Weather>> = weatherDao.sortByLowPriority()
//    val sortByHighPriority: Flow<List<Weather>> = weatherDao.sortByHighPriority()

//    fun getSelectedTask(taskId: Int): Flow<ToDoTask> {
//        return toDoDao.getSelectedTask(taskId = taskId)
//    }
//

    suspend fun addLocation(location: Location) {
        weatherDao.addLocation(location = location)
    }

    suspend fun addWeather(weather: Weather) {
        weatherDao.addWeather(weather = weather)
    }

//
//    suspend fun updateTask(toDoTask: ToDoTask) {
//        weatherDao.updateTask(toDoTask = toDoTask)
//    }
//
//    suspend fun deleteTask(toDoTask: ToDoTask) {
//        weatherDao.deleteTask(toDoTask = toDoTask)
//    }
//
//    suspend fun deleteAllTasks() {
//        weatherDao.deleteAllTasks()
//    }
//
//    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>> {
//        return toDoDao.searchDatabase(searchQuery = searchQuery)
//    }
}