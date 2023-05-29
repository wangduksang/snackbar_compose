package com.example.weathercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.weathercompose.data.models.Location
import com.example.weathercompose.data.models.Weather
import com.example.weathercompose.ui.screens.dashboard.WeatherContent
import com.example.weathercompose.ui.theme.WeathercomposeTheme
import com.example.weathercompose.ui.viewmodels.WeatherViewModel
import com.example.weathercompose.util.RequestState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        weatherViewModel.requestWeatherApi()
        weatherViewModel.bindWeatherDataWithFlow()

        // for data test
        val scope = CoroutineScope(EmptyCoroutineContext)
        lateinit var data : Map<Location, List<Weather>>
        scope.launch {
            weatherViewModel.allWeatherData.collect { requestState ->
                if (requestState is RequestState.Success<*>) {
                    val data = requestState.data as Map<Location, List<Weather>>
                    val iterator = data.iterator()

                    while (iterator.hasNext()) {
                        val locMap = iterator.next()
                        println(" locMap.key => $locMap")
                    }
                }
            }
        }

        setContent {
            WeathercomposeTheme {
                WeatherContent(weatherViewModel)
            }
        }
    }
}


