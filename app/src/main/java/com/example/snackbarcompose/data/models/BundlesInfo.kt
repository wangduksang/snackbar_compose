package com.example.snackbarcompose.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.snackbarcompose.util.Constants

@Entity(tableName = Constants.BUNDLES_INFO_TABLE)
data class BundlesInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "bundles_id")
    val bundlesId: String = "",
)
