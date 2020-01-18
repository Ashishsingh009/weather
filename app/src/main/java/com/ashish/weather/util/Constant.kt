package com.ashish.weather.util

import java.text.SimpleDateFormat
import java.util.*

class Constant {
    companion object {
        const val BASE_URL: String = "https://api.openweathermap.org/"
        const val API_KEY: String = "e7c7c90365190f0a6c0329e1bf041087"
        const val CITY_TEMP_API: String = "data/2.5/weather?"
        const val FORECAST_API: String = "data/2.5/forecast?"
        const val QUERY: String = "q"
        const val APP_ID: String = "appid"
        const val UNITS: String = "units"
        const val METRIC: String = "metric"
        const val PARSE_DATA: String = "parse_data"
        const val FORECAST_DATA: String = "FORECAST_DATA"


        @JvmStatic
        fun getTimeMilliSec(milliSec: Long): String? {
            return try {
                val dt = SimpleDateFormat("yyyy-MM-dd hh:mm")
                val netDate = Date(milliSec * 1000)

                dt.format(netDate).toString()
            } catch (e: Exception) {
                e.toString()
            }
        }
        @JvmStatic
        fun getHoursMilliSec(milliSec: Long): String? {
            return try {
                val dt = SimpleDateFormat("hh:mm:ss aa")
                val netDate = Date(milliSec * 1000)

                dt.format(netDate).toString()
            } catch (e: Exception) {
                e.toString()
            }
        }

        @JvmStatic
        fun getMonthName(milliSec: Long): String? {
            return try {
                val dt = SimpleDateFormat("MMM-dd hh")
                val netDate = Date(milliSec * 1000)

                dt.format(netDate).toString()
            } catch (e: Exception) {
                e.toString()
            }
        }
    }
}