package com.ashish.weather.model.forecast

import com.ashish.weather.model.Clouds
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class List (

	@SerializedName("dt") val dt : Int,
	@SerializedName("main") val main : Main,
	@SerializedName("weather") val weather : ArrayList<Weather>,
	@SerializedName("clouds") val clouds : Clouds,
	@SerializedName("wind") val wind : Wind,
	@SerializedName("sys") val sys : Sys,
	@SerializedName("dt_txt") val dt_txt : String
):Serializable