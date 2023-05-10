package com.example.snackbarcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.snackbarcompose.data.models.ToDoTask

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class SnackBarDatabase: RoomDatabase() {

    // specify dao

}