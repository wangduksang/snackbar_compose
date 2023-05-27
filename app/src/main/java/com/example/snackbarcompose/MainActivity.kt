package com.example.snackbarcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.snackbarcompose.data.models.Location
import com.example.snackbarcompose.data.models.Weather
import com.example.snackbarcompose.ui.screens.dashboard.WeatherContent
import com.example.snackbarcompose.ui.theme.SnackbarComposeTheme
import com.example.snackbarcompose.ui.viewmodels.WeatherViewModel
import com.example.snackbarcompose.util.RequestState
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
        weatherViewModel.bindWeatherDataWithDB()

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
            SnackbarComposeTheme {
                WeatherContent(weatherViewModel)
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    SnackbarComposeTheme {
//        Greeting("Android")
//    }
//}

