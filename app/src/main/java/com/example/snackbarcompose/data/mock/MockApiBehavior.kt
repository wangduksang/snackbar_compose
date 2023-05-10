package com.example.snackbarcompose.data.mock
import android.content.Context
import com.google.gson.Gson

fun mockApi(context: Context) =
    createFlowMockApi(
        MockNetworkInterceptor()
            .mock(
                path = "/current-stock-prices",
                body = {
                    Gson().toJson(fakeCurrentStockPrices(context))
                },
                status = 200,
                delayInMs = 1500,
                errorFrequencyInPercent = 50
            )
    )
