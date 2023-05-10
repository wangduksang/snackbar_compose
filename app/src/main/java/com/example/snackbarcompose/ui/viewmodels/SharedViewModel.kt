package com.example.snackbarcompose.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.snackbarcompose.data.mock.FlowMockApi
import com.example.snackbarcompose.data.models.Category
import com.example.snackbarcompose.data.models.ImageArrange
import com.example.snackbarcompose.data.models.Item
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val mockApi: FlowMockApi
//    private val repository: ToDoRepository,
//    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    fun makeMockData() {
        //        data class Category(
        //            val title: String,
        //            val itemList: List<Item>,
        //        )
        //
        //        data class Item(
        //            val title: String,
        //            val descInImage: String,
        //            val isClock: Boolean,
        //            val desc: String,
        //            val rate: Float,
        //            val distance: String,
        //            val imageArrange: ImageArrange,
        //        )

        val item11 = Item(title = "Review 10 snacks of guilty pleasure", hasClock = FALSE)
        val item12 = Item(title = "Review 10 snacks good for your health", hasClock = FALSE)
        val item13 = Item(title = "Good afternoon. Take a break from work.", hasClock = FALSE)
        val item14 = Item(title = "Good afternoon. Take a break from work.", hasClock = FALSE)

        val itemList1 = arrayListOf(item11, item12, item13, item14)
        val category1 = Category(
            title = "Daily quests",
            itemList = itemList1,
            imageArrange = ImageArrange.NONE
        )

        val item21 = Item(
            title = "Dalgona c...",
            desc = "Korean style",
            hasClock = TRUE,
            distance = "20m",
            rate = 5.0F
        )
        val item22 = Item(
            title = "Seeds",
            desc = "For afternoon t...",
            hasClock = TRUE,
            distance = "32m",
            rate = 4.9F
        )
        val item23 = Item(
            title = "Taiyaki",
            desc = "Japanese ice...",
            hasClock = TRUE,
            distance = "16m",
            rate = 4.9F
        )

        val itemList2 = arrayListOf(item21, item22, item23)
        val category2 = Category(
            title = "Recommended for you",
            itemList = itemList2,
            imageArrange = ImageArrange.NONE,
        )

        val item31 = Item(
            title = "Googie Googie Cookie",
            desc = "Good for your teeth.",
            hasClock = TRUE,
            distance = "24m",
            rate = 4.9F
        )
        val item32 = Item(
            title = "Oreo cereal",
            desc = "3x pleasure",
            hasClock = TRUE,
            distance = "12m",
            rate = 4.2F
        )
        val item33 = Item(
            title = "Googie Googie Cookie",
            desc = "Good for your teeth.",
            hasClock = TRUE,
            distance = "24m",
            rate = 4.9F
        )

        val itemList3 = arrayListOf(item31, item32, item33)
        val category3 = Category(
            title = "Seasonal bundles",
            itemList = itemList3,
            imageArrange = ImageArrange.NONE,
        )

        val item41 = Item(
            title = "On diet",
            desc = "4-snack pack",
            hasClock = TRUE,
            distance = "20m",
            rate = 5.0F
        )
        val item42 = Item(
            title = "Date night",
            desc = "6-snack pack",
            hasClock = TRUE,
            distance = "32m",
            rate = 4.9F
        )
        val item43 = Item(
            title = "Picnics",
            desc = "8-snack pack",
            hasClock = TRUE,
            distance = "16m",
            rate = 4.9F
        )

        val itemList4 = arrayListOf(item41, item42, item43)
        val category4 = Category(
            title = "Mystery bundles",
            itemList = itemList4,
            imageArrange = ImageArrange.MANY,
        )

        val gson = Gson()
        val categoryList = arrayListOf(category1, category2, category3, category4)
        Log.d("categoryList", categoryList.toString())
    }
}