package com.ashish.weather.model

import com.google.gson.annotations.SerializedName

data class ChartData (
    @SerializedName("date") var date : String,
    @SerializedName("temp") var temp : String
)

