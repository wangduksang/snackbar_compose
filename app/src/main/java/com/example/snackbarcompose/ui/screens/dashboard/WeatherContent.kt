package com.example.snackbarcompose.ui.screens.dashboard

import android.graphics.drawable.Drawable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.snackbarcompose.data.models.Location
import com.example.snackbarcompose.data.models.LocationWeather
import com.example.snackbarcompose.data.models.Weather
import com.example.snackbarcompose.data.models.constance.WeatherDecorType
import com.example.snackbarcompose.ui.viewmodels.WeatherViewModel
import com.example.snackbarcompose.ui.viewmodels.WeatherViewModel.Companion.locationWeatherList
import com.example.snackbarcompose.ui.viewmodels.WeatherViewModel.Companion.weatherBufferList
import com.example.snackbarcompose.util.RequestState
import com.example.snackbarcompose.util.U_CODE_CELCIUS
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherContent(weatherViewModel: WeatherViewModel) {

    val weatherState by weatherViewModel.allWeatherData.collectAsState()

    if (weatherState is RequestState.Success) {
        val weatherListMap =
            (weatherState as RequestState.Success<Map<Location, List<Weather>>>).data
        //println("data.size => ${data.size}")
        //weatherBufferList.putAll(weatherList)
        weatherViewModel.convertToList(weatherListMap)

        val groupedItem = locationWeatherList.groupBy {
            it.name
        }
        println("weatherBufferList ${weatherBufferList.size}")

        LazyColumn {
            groupedItem.forEach { (location, weatherList) ->
                println("draw lazy column")

                //println("location : ${location.name} weather list.size => ${weatherList.size}")
                stickyHeader {
                    Text(
                        text = location,
                        color = Color.White,
                        modifier = Modifier
                            .background(color = Color.Black)
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                }
                items(
                    items = weatherList,
                ) { weather ->
                    WeatherListItem(
                        item = weather,
                        weatherViewModel = weatherViewModel
                    )
                }
            }
        }
    }
}

@Composable
private fun WeatherListItem(
    item: LocationWeather,
    weatherViewModel: WeatherViewModel
) {

    var isLoading by remember { mutableStateOf(true) }

    val sortedDate = weatherViewModel.getDayOfTheWeek(item.dt)
    println("sortedDate => $sortedDate")

    Column {

        Row {
            Box(
                Modifier
                    .fillMaxSize()
                    .align(alignment = Alignment.Top)
                //.background(placeholderColor)
            ) {
                Text(
                    text = sortedDate,
                    color = Color.Black,
                    modifier = Modifier
                        .background(Color.White)
                        .padding(5.dp)
                        .align(Alignment.TopStart)
                )
            }
        }

        Row {
            val imageUrl = getImageUrl(item)

            WeatherImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                contentScale = ContentScale.Fit,
                alignment = Alignment.BottomStart
            ) {
                it.addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        isLoading = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        isLoading = false
                        return false
                    }
                })
            }

            Box(
                Modifier
                    .fillMaxSize()
                    .align(alignment = Alignment.Bottom)
                //.background(placeholderColor)
            ) {
                //Text(modifier = Modifier.align(Alignment.TopStart), text = "1")

                Text(
                    text = item.state,
                    color = Color.Black,
                    modifier = Modifier
                        .background(Color.White)
                        .padding(5.dp)
                        .align(Alignment.BottomStart)
                )

                Text(
                    text = "Max : ${item.tempMax.roundToInt()} $U_CODE_CELCIUS",
                    color = Color.Black,
                    modifier = Modifier
                        .background(Color.White)
                        .padding(5.dp)
                        .align(Alignment.BottomCenter)
                )

                Text(
                    text = "Min : ${item.tempMin.roundToInt()} $U_CODE_CELCIUS",
                    color = Color.Black,
                    modifier = Modifier
                        .background(Color.White)
                        .padding(5.dp)
                        .align(Alignment.BottomEnd)
                )
            }
        }
    }
}

@Composable
private fun getImageUrl(item: LocationWeather): Int {
    println("item.state => $item.state")
    val imageUrl = when (item.state) {
        WeatherDecorType.LIGHT_RAIN.word -> WeatherDecorType.LIGHT_RAIN.icon
        WeatherDecorType.BROKEN_CLOUDS.word -> WeatherDecorType.BROKEN_CLOUDS.icon
        WeatherDecorType.SCATTERED_CLOUDS.word -> WeatherDecorType.SCATTERED_CLOUDS.icon
        WeatherDecorType.OVERCAST_CLOUDS.word -> WeatherDecorType.OVERCAST_CLOUDS.icon
        WeatherDecorType.FEW_CLOUDS.word -> WeatherDecorType.FEW_CLOUDS.icon
        WeatherDecorType.CLEAR_SKY.word -> WeatherDecorType.CLEAR_SKY.icon
        else -> {
            WeatherDecorType.NONE.icon
        }
    }
    return imageUrl
}

