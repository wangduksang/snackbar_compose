package com.example.snackbarcompose.ui.screens.dashboard

import android.graphics.drawable.Drawable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.RequestBuilderTransform
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.snackbarcompose.data.models.Location
import com.example.snackbarcompose.data.models.Weather
import com.example.snackbarcompose.data.models.constance.WeatherDecorType
import com.example.snackbarcompose.ui.viewmodels.WeatherViewModel
import com.example.snackbarcompose.util.RequestState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherContent(weatherViewModel: WeatherViewModel) {

    val weatherState by weatherViewModel.allWeatherData.collectAsState()

    if (weatherState is RequestState.Success) {
        val data = (weatherState as RequestState.Success<Map<Location, List<Weather>>>).data
        LazyColumn {

            data.forEach { (location, models) ->

                stickyHeader {
                    Text(
                        text = location.name,
                        color = Color.White,
                        modifier = Modifier
                            .background(color = Color.Black)
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                }

                items(models) { model ->
                    WeatherListItem(
                        item = model,
                        imageHeight = 100.dp,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
    }


}

@Composable
private fun WeatherListItem(
    item: Weather,
    imageHeight: Dp,
    modifier: Modifier = Modifier,
    placeholderColor: Color = MaterialTheme.colorScheme.onSurface.copy(0.2f)
) {

    var isLoading by remember { mutableStateOf(true) }

    Box(
        modifier
            .fillMaxWidth()
            .height(imageHeight)
    ) {
        if (isLoading) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(placeholderColor)
            )
        }

        Text(
            text = item.state,
            color = Color.White,
            modifier = Modifier
                .background(Color.Gray)
                .padding(5.dp)
                .fillMaxWidth()
        )

        val imageUrl = when (item.state) {
            WeatherDecorType.LIGHT_RAIN.word -> WeatherDecorType.LIGHT_RAIN.icon
            WeatherDecorType.BROKEN_CLOUDS.word -> WeatherDecorType.BROKEN_CLOUDS.icon
            WeatherDecorType.SCATTERED_CLOUDS.word -> WeatherDecorType.SCATTERED_CLOUDS.icon
            WeatherDecorType.FEW_CLOUDS.word -> WeatherDecorType.FEW_CLOUDS.icon
            WeatherDecorType.CLEAR_SKY.word -> WeatherDecorType.CLEAR_SKY.icon
            else -> {
                WeatherDecorType.NONE.icon
            }
        }

        WeatherImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
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
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WeatherImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    requestBuilderTransform: RequestBuilderTransform<Drawable> = { it },
) {
    if (LocalInspectionMode.current) {

        Box(modifier = modifier.background(Color.Magenta))
        return
    }
    GlideImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter,
        requestBuilderTransform = requestBuilderTransform
    )
}