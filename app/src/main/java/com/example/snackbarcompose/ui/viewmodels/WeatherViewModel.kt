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
import com.example.snackbarcompose.util.getDateString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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
        weatherList.add(WeatherParam(37.5600F, 126.9900F, APP_KEY))
        weatherList.add(WeatherParam(51.5072F, -0.1275F, APP_KEY))
        weatherList.add(WeatherParam(41.8375F, -87.6866F, APP_KEY))
    }

    // reqeust datasource .. DB or API
    fun requestWeatherApi() {

        viewModelScope.launch {
            // consume
            weatherFlowList.collect { resp ->
                //println("data => $resp")
                // 5일치

                val location = Location()
                resp.city?.apply {
                    location.name = name.toString()
                    location.longitude = coord?.lon as Float
                    location.latitude = coord?.lat as Float
                }

                repository.addLocation(location = location)

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

                val weatherList = weatherHashMap.toList().take(SHOW_LIST_CNT)

                weatherList.forEach { list ->

                    val weather = Weather().apply {
                        val item = list.second

                        item.main.apply {
                            tempMin = tempMin as? Float ?: 0.0F
                            tempMax = tempMin as? Float ?: 0.0F
                        }
                        dt = item.dtTxt.toString()
                    }
                    repository.addWeather(weather = weather)
                }
            }
        }
    }

    //weatherFlowList.launchIn(viewModelScope)

    private fun toDay(listItem: ListItem?): String {

        val dateTime: Int = if (listItem != null)
            listItem.dt!! else -1
        val dateTimeStr = getDateString(dateTime)
        println("date time => $dateTimeStr")
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