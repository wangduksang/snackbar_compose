package com.example.snackbarcompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snackbarcompose.data.models.Location
import com.example.snackbarcompose.data.models.Weather
import com.example.snackbarcompose.data.remote.ListItem
import com.example.snackbarcompose.data.remote.WeatherResponse
import com.example.snackbarcompose.data.repositories.WeatherRepository
import com.example.snackbarcompose.network.WeatherApi
import com.example.snackbarcompose.util.Constants.APP_KEY
import com.example.snackbarcompose.util.RequestState
import com.example.snackbarcompose.util.getCelcius
import com.example.snackbarcompose.util.getDateString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WeatherParam(
    var longitude: Float,
    var latitude: Float,
    val APP_KEY: String,
)

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherApi: WeatherApi,
    private val repository: WeatherRepository

) : ViewModel() {

    companion object {
        const val SHOW_LIST_CNT = 5
    }

    // Seoul 37.5600	126.9900
    // London 51.5072 -0.1275
    // Chicago 41.8375 -87.6866
    val weatherList = arrayListOf<WeatherParam>()

    init {

        weatherList.add(WeatherParam(37.5519F, 126.9918F, APP_KEY))
        weatherList.add(WeatherParam(51.5072F, -0.1275F, APP_KEY))
        weatherList.add(WeatherParam(41.8375F, -87.6866F, APP_KEY))
    }

    // hot flow
    // mutable to immutable
    private val _allWeatherData =
        MutableStateFlow<RequestState<Map<Location, List<Weather>>>>(RequestState.Idle)
    val allWeatherData: StateFlow<RequestState<Map<Location, List<Weather>>>> = _allWeatherData

    fun bindWeatherDataWithDB() {
        _allWeatherData.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getAllDataMap.collect {
                    _allWeatherData.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allWeatherData.value = RequestState.Error(e)
        }
    }

    fun requestWeatherApi() {

        // check db

        viewModelScope.launch {
            // consume
            weatherFlowList.collect { resp ->
                //println("data => $resp")
                // 5day
                addLocationAndWeatherListToRepo(resp)
//                val locationId = addLocationToRepo(resp)
//                println("locationId :  $locationId")
//                addWeatherToRepo(locationId, resp)
            }
        }
    }

    // 3times
    private suspend fun addLocationAndWeatherListToRepo(resp: WeatherResponse) {

        val location = Location()
        resp.city?.apply {
            println("city name :  ${name.toString()}")
            location.name = name.toString()

            location.longitude = coord?.lon as Double
            location.latitude = coord?.lat as Double

            println("city longitude :  ${location.longitude}")
            println("city latitude :  ${location.latitude}")
        }

        val weatherHashMap = HashMap<String, ListItem>()

        resp.list?.let { list ->
            list.map { item ->
                if (item != null) {
                    item.day = toDay(item)
                }
            }

            list.forEach { item ->
                if (item != null) {
                    weatherHashMap[item.day] = item
                }
            }
        }

        val weatherList = ArrayList<Weather>()
        weatherHashMap.toList().take(SHOW_LIST_CNT).forEach { list ->

            val weather = Weather().apply {
                val item = list.second
                var main = item.main

                tempMin = getCelcius(main?.tempMin as Double)
                tempMax = getCelcius(main.tempMax as Double)

//                println("tempMin :  $tempMin")
//                println("tempMax :  $tempMax")

                dt = item.dtTxt.toString()
            }
            weatherList.add(weather)

        }
        repository.addLocationAndWeatherList(location = location, weatherList = weatherList)
    }

//    private suspend fun addLocationToRepo(resp: WeatherResponse): Long {
//
//        return repository.addLocation(location = location)
//    }

    //weatherFlowList.launchIn(viewModelScope)

    private fun toDay(listItem: ListItem?): String {

        val dateTime: Int = if (listItem != null)
            listItem.dt!! else -1
        val dateTimeStr = getDateString(dateTime)
        //println("date time => $dateTimeStr")
        return dateTimeStr
    }

    // procedure
    private val weatherFlowList: Flow<WeatherResponse> = flow {

        weatherList.forEach {
            val weatherResponse = weatherApi.getWeather(
                it.latitude.toString(),
                it.longitude.toString(),
                APP_KEY
            )
            emit(weatherResponse)
        }
    }
}