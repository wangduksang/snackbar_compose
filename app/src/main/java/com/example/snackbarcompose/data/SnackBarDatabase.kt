package com.example.snackbarcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.snackbarcompose.data.models.Bundles
import com.example.snackbarcompose.data.models.BundlesInfo
import com.example.snackbarcompose.data.models.Category
import com.example.snackbarcompose.data.models.Item
import com.example.snackbarcompose.data.models.Market
import com.example.snackbarcompose.data.models.User

@Database(
    entities = [
        Bundles::class,
        BundlesInfo::class,
        Category::class,
        Item::class,
        Market::class,
        User::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class SnackBarDatabase : RoomDatabase() {

    // specify dao
    //abstract fun toDoDao(): ToDoDao
}