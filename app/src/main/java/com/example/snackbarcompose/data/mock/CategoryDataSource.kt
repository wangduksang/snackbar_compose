package com.example.snackbarcompose.data.mock

import com.example.snackbarcompose.data.models.Category
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import retrofit2.HttpException

interface CategoryDataSource {
    val latestStockList: Flow<List<Category>>
}

class NetworkCategoryDataSource(mockApi: FlowMockApi) : CategoryDataSource {

    override val latestStockList: Flow<List<Category>> = flow {
        while (true) {
            //Timber.tag("Flow").d("Fetching current stock prices")
            val currentStockList = mockApi.getCurrentStockPrices()
            emit(currentStockList)
            delay(5_000)
        }
    }.retry { cause ->
        //Timber.tag("Flow").d("Enter retry operator with $cause")
        val shouldRetry = cause is HttpException
        if (shouldRetry) {
            delay(5000)
        }
        shouldRetry
    }
}