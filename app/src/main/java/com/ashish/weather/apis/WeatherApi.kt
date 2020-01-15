package com.ashish.weather.apis

import com.ashish.weather.model.JSONWeatherSet
import com.ashish.weather.model.forecast.JSONForecast
import com.ashish.weather.util.Constant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {

    /**
     * Api to fetch weather from server.
     * @return Call instance for this api.
     */
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET(Constant.CITY_TEMP_API)
    fun getWeatherDetail(@Query(Constant.QUERY) id: String?, @Query(Constant.UNITS) unit: String?, @Query(Constant.APP_ID) appID: String?): Call<JSONWeatherSet?>?



    /**
     * Api to fetch weather from server.
     * @return Call instance for this api.
     */
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET(Constant.FORECAST_API)
    fun getWeatherForecast(@Query(Constant.QUERY) id: String?, @Query(Constant.UNITS) unit: String?, @Query(Constant.APP_ID) appID: String?): Call<JSONForecast?>?

}