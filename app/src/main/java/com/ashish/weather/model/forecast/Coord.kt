package com.ashish.weather.model.forecast
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Coord (

	@SerializedName("lat") val lat : Double,
	@SerializedName("lon") val lon : Double
):Serializable