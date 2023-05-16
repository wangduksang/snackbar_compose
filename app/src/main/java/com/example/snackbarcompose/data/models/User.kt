package com.example.snackbarcompose.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.snackbarcompose.util.Constants

@Entity(tableName = Constants.USER_TABLE)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "email")
    val email: String = "",
    @ColumnInfo(name = "passwd")
    val passwd: String = "",
)
