package com.ashish.weather.model.forecast

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class City (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("coord") val coord : Coord,
	@SerializedName("country") val country : String
):Serializable