package com.example.weathercompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.test.platform.app.InstrumentationRegistry
import com.example.weathercompose.data.models.Location
import com.example.weathercompose.data.models.Weather
import com.example.weathercompose.data.remote.ListItem
import com.example.weathercompose.data.remote.WeatherResponse
import com.example.weathercompose.data.repositories.WeatherRepository
import com.example.weathercompose.network.WeatherApi
import com.example.weathercompose.util.Constants.APP_KEY
import com.example.weathercompose.util.RequestState
import com.example.weathercompose.util.getCelcius
import com.example.weathercompose.util.getDateString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Test
import javax.inject.Inject

data class WeatherParam(
    var longitude: Float,
    var latitude: Float,
    val APP_KEY: String,
)

@HiltViewModel
class WeatherViewModel {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.weathercompose", appContext.packageName)
    }
}