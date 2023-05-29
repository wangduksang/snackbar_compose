package com.example.weathercompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathercompose.data.models.Location
import com.example.weathercompose.data.models.LocationWeather
import com.example.weathercompose.data.models.Weather
import com.example.weathercompose.data.remote.ListItem
import com.example.weathercompose.data.remote.WeatherResponse
import com.example.weathercompose.data.repositories.WeatherRepository
import com.example.weathercompose.network.WeatherApi
import com.example.weathercompose.ui.screens.dashboard.param.WeatherParam
import com.example.weathercompose.util.Constants.APP_KEY
import com.example.weathercompose.util.RequestState
import com.example.weathercompose.util.getCelcius
import com.example.weathercompose.util.getDateString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlin.math.abs


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherApi: WeatherApi,
    private val repository: WeatherRepository

) : ViewModel() {

    // Seoul     37.5600     126.9900
    // London    51.5072    -0.1275
    // Chicago   41.8375    -87.6866
    val weatherList = arrayListOf<WeatherParam>()

    init {

        weatherList.add(WeatherParam(37.5519F, 126.9918F, APP_KEY))
        weatherList.add(WeatherParam(51.5072F, -0.1275F, APP_KEY))
        weatherList.add(WeatherParam(41.8375F, -87.6866F, APP_KEY))
    }

    companion object {
        const val SHOW_LIST_CNT = 5

        const val TODAY = "Today"
        const val TOMORROW = "Tomorrow"

        val locationWeatherList = mutableListOf<LocationWeather>()
        val weatherBufferList: MutableMap<Location, List<Weather>> = mutableMapOf()
        fun getWeatherBufferList(): Int {
            var weatherBufferListSize: Int = 0
            weatherBufferList.forEach {
                weatherBufferListSize += it.value.size
            }
            return weatherBufferListSize
        }
    }

    fun convertToList(weatherListMap: Map<Location, List<Weather>>) {
        weatherListMap.forEach {
            val location = it.key
            val weatherList = it.value

            weatherList.forEach { weather ->
                val locationWeather = LocationWeather().apply {
                    latitude = location.latitude
                    longitude = location.longitude
                    name = location.name
                    tempMax = weather.tempMax
                    tempMin = weather.tempMin
                    state = weather.state
                    desc = weather.desc
                    dt = weather.dt
                    dtTxt = weather.dtTxt
                }

                locationWeatherList.add(locationWeather)
            }
        }
    }

    // cold flow
    // mutable to immutable
    private val _allWeatherData =
        MutableStateFlow<RequestState<Map<Location, List<Weather>>>>(RequestState.Idle)
    val allWeatherData: StateFlow<RequestState<Map<Location, List<Weather>>>> = _allWeatherData

    fun bindWeatherDataWithFlow() {
        _allWeatherData.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.deleteAllData()
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

    // 3 times
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

        groupedByDay(resp, weatherHashMap)

        val weatherList = ArrayList<Weather>()
        weatherHashMap
            .toList()
            .take(SHOW_LIST_CNT)
            .forEach { list ->

                val weather = Weather().apply {
                    val item = list.second
                    var main = item.main
                    var weather = item.weather?.get(0)

                    tempMin = getCelcius(main?.tempMin as Double)
                    tempMax = getCelcius(main.tempMax as Double)

                    state = weather?.main ?: ""
                    desc = weather?.description ?: ""

                    dtTxt = item.dtTxt.toString()

                    // yyyy-MM-dd => yyyyMMdd
                    val squashedDate = dtTxt.take(10).replace("-", "")

                    val strCurrentDate = convertDateAsForSort(squashedDate)
                    dt = strCurrentDate?.toInt()!!
                    //dt = convertTime(strCurrentDate)
                }

                weatherList.add(weather)
                weatherList.sortByDescending {
                    it.dt
                }
                weatherList.forEach { weather ->
                    println("weatherItem :  ${weather.dtTxt}")
                }

            }
        repository.addLocationAndWeatherList(location = location, weatherList = weatherList)
    }

    private fun convertDateAsForSort(squashedDate: String): String? {
        val currentDate =
            LocalDate.parse(squashedDate, DateTimeFormatter.BASIC_ISO_DATE)
        val dtf = DateTimeFormatter.ofPattern("ddMMyyyy")
        return currentDate.format(dtf)
    }

    private fun groupedByDay(
        resp: WeatherResponse,
        weatherHashMap: HashMap<String, ListItem>
    ) {
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
    }

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
    }.retryWhen { cause, attempt ->
        println("Enter retry() with $cause")
        delay(1000 * attempt)
        cause is NetworkException
    }

    class NetworkException(message: String) : Exception(message)

    fun getDayOfTheWeek(dateStr: String): String {

        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = format.parse(dateStr)
        val dateInfoList = date.toString().split(" ")

        val translatedDay = when (getDayAfterToday(date = date)) {
            0L -> TODAY
            1L -> TOMORROW
            else -> String.format("${dateInfoList[0]} ${dateInfoList[2]} ${dateInfoList[1]}")
        }

        return if (!dateInfoList.isNullOrEmpty() && dateInfoList.size > 2)
            translatedDay
        else
            ""
    }

    private fun getDayAfterToday(date: Date): Long {
        var today = Calendar.getInstance()
        val secOfDay = (60 * 60 * 24 * 1000)
        val halfSecOfDay = secOfDay / 2
        val todayFromDate = (today.time.time - date.time)
        var calcDate = todayFromDate / (60 * 60 * 24 * 1000)
        println("calcDate $calcDate")
        if (abs(calcDate) == 0L) {
            val todayDecision = todayFromDate.toFloat() / halfSecOfDay
            println("todayDecision $todayDecision")
            return if (todayDecision > 0L) 1L else 0L
        } else {
            return calcDate
        }
    }
}