package com.example.weathercompose.data.remote

import com.google.gson.annotations.SerializedName

data class SearchCorpRequest(
    @SerializedName("crpNm")
    var crpNm: String

)