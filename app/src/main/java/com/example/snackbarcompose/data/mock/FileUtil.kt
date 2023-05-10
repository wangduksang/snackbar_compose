package com.example.snackbarcompose.data.mock

import android.content.Context

object FileUtil {

    fun getAssetPathList(context: Context) : List<String> {

        //val rootPath = "main/src/assets"
        val rootPath = ""
        val imageList : ArrayList<String> = ArrayList()
        context.assets.list(rootPath)?.forEach {
            imageList.add(it)
            println(it)
        }
        return imageList
    }
}