package com.example.snackbarcompose.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.snackbarcompose.data.mock.FlowMockApi
import com.example.snackbarcompose.data.models.Category
import com.example.snackbarcompose.data.models.Item
import com.example.snackbarcompose.data.models.constance.ItemDecorType
import com.example.snackbarcompose.data.models.constance.Priority
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val mockApi: FlowMockApi
//    private val repository: ToDoRepository,
//    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    fun makeMockData() {

        val item11 = Item(id = 1, itemsId = 1, desc = "Review 10 snacks of guilty pleasure")
        val item12 = Item(id = 1, itemsId = 2, desc = "Review 10 snacks good for your health")
        val item13 = Item(id = 1, itemsId = 3, desc = "Good afternoon. Take a break from work1")
        val item14 = Item(id = 1, itemsId = 4, desc = "Good afternoon. Take a break from work2")

        val itemList1 = arrayListOf(item11, item12, item13, item14)
        val category1 = Category(
            id = 1,
            title = "Daily quests",
            priority = Priority.HIGH,
            decorType = ItemDecorType.IMAGE_SINGLE_OVERLAPPING_TEXT.type,
            itemList = itemList1,
        )

        val category1Str = category1.toString()
        Timber.tag("wds").d("category1 => %s", category1Str)

        val item21 = Item(
            id = 2,
            itemsId = 1,
            summary = "Korean style",
            desc = "Dalgona c...",
            distance = "20m",
            rate = 5.0F
        )
        val item22 = Item(
            id = 2,
            itemsId = 2,
            summary = "For afternoon t...",
            desc = "Review 10 snacks good for your health",
            distance = "32m",
            rate = 4.9F
        )
        val item23 = Item(
            id = 2,
            itemsId = 3,
            summary = "Japanese ice...",
            desc = "Good afternoon. Take a break from work1",
            distance = "16m",
            rate = 4.9F
        )
        val item24 = Item(
            id = 2,
            itemsId = 4,
            summary = "Japanese ice...2",
            desc = "Good afternoon. Take a break from work2",
            distance = "16m",
            rate = 4.9F
        )

        val itemList2 = arrayListOf(item21, item22, item23, item24)
        val category2 = Category(
            id = 2,
            title = "Popular items",
            priority = Priority.HIGH,
            decorType = ItemDecorType.IMAGE_SINGLE_NARROW.type,
            itemList = itemList2,
        )

        val item31 = Item(
            id = 3, itemsId = 1,
            summary = "Googie Googie Cookie",
            desc = "Good for your teeth.",
            distance = "24m",
            rate = 4.9F
        )
        val item32 = Item(
            id = 3, itemsId = 2,
            summary = "Oreo cereal",
            desc = "3x pleasure",
            distance = "12m",
            rate = 4.2F
        )
        val item33 = Item(
            id = 3, itemsId = 3,
            summary = "Googie Googie Cookie",
            desc = "Good for your teeth.",
            distance = "24m",
            rate = 4.9F
        )

        val itemList3 = arrayListOf(item31, item32, item33)
        val category3 = Category(
            id = 3,
            title = "Popular items",
            priority = Priority.MEDIUM,
            decorType = ItemDecorType.IMAGE_SINGLE_WIDE.type,
            itemList = itemList3,
        )

        val item41 = Item(
            id = 4, itemsId = 1,
            desc = "Mooncakes",
            distance = "42m",
            rate = 4.9F
        )
        val item42 = Item(
            id = 4, itemsId = 2,
            desc = "Dry fruits",
            distance = "32m",
            rate = 4.7F
        )
        val item43 = Item(
            id = 4, itemsId = 3,
            desc = "Nuts",
            distance = "15m",
            rate = 4.2F
        )

        val itemList4 = arrayListOf(item41, item42, item43)
        val category4 = Category(
            title = "Mystery bundles",
            priority = Priority.MEDIUM,
            decorType = ItemDecorType.IMAGE_GRID_4_SEASONAL_BUNDLE.type,
            itemList = itemList4,
        )

        val item51 = Item(
            id = 5, itemsId = 1,
            summary = "On diet",
            desc = "4-snack pack",
            distance = "20m",
            rate = 5.0F
        )
        val item52 = Item(
            id = 5, itemsId = 2,
            summary = "Date night",
            desc = "6-snack pack",
            distance = "32m",
            rate = 4.9F
        )
        val item53 = Item(
            id = 5, itemsId = 3,
            summary = "Picnics",
            desc = "8-snack pack",
            distance = "16m",
            rate = 4.9F
        )

        val itemList5 = arrayListOf(item51, item52, item53)
        val category5 = Category(
            title = "Mystery bundles",
            priority = Priority.LOW,
            decorType = ItemDecorType.IMAGE_GRID_4_SEASONAL_BUNDLE.type,
            itemList = itemList5,
        )

        val categoryList = arrayListOf(category1, category2, category3, category4, category5)

        println(categoryList)

        val jsonTut: String = Gson().toJson(categoryList)
        println(jsonTut)
    }
}