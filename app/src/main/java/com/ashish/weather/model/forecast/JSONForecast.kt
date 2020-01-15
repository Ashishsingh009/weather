package com.ashish.weather.model.forecast

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class JSONForecast (

	@SerializedName("cod") val cod : Int,
	@SerializedName("message") val message : Double,
	@SerializedName("cnt") val cnt : Int,
	@SerializedName("list") val list : ArrayList<List>,
	@SerializedName("city") val city : City
):Serializable
