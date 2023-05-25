package com.example.snackbarcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavHostController
import com.example.snackbarcompose.data.models.Location
import com.example.snackbarcompose.data.models.Weather
import com.example.snackbarcompose.ui.theme.SnackbarComposeTheme
import com.example.snackbarcompose.ui.viewmodels.WeatherViewModel
import com.example.snackbarcompose.util.RequestState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val weatherViewModel: WeatherViewModel by viewModels()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("wds", "hello world")
        // runtime exp
        weatherViewModel.requestWeatherApi()
        weatherViewModel.bindWeatherDataWithDB()

        val scope = CoroutineScope(EmptyCoroutineContext)
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

//                navController = rememberAnimatedNavController()
//                SetupNavigation(
//                    navController = navController
//                )

//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
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

