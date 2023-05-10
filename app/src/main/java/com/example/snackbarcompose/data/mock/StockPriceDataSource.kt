package com.example.snackbarcompose.data.mock

import com.example.snackbarcompose.data.models.Category
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

interface StockPriceDataSource {
    val categoryList: Flow<List<Category>>
}

class NetworkStockPriceDataSource(mockApi: FlowMockApi) : StockPriceDataSource {

    override val categoryList: Flow<List<Category>> = flow {
        while (true) {
            Timber.tag("Flow").d("Fetching current stock prices")
            val currentStockList = mockApi.getCurrentStockPrices()
            emit(currentStockList)
            delay(5_000)
        }
    }
}
