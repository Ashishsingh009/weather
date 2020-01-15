package com.ashish.weather.model.forecast


import com.google.gson.annotations.SerializedName
import java.io.Serializable




data class Sys (

	@SerializedName("pod") val pod : String
):Serializable