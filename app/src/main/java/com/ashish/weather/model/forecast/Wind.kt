package com.ashish.weather.model.forecast


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Wind (

	@SerializedName("speed") val speed : Double,
	@SerializedName("deg") val deg : Double
):Serializable