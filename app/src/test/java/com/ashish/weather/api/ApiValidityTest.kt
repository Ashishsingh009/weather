package com.ashish.weather.api

import com.ashish.weather.apis.WeatherApi
import com.ashish.weather.model.JSONWeatherSet
import com.ashish.weather.util.Constant
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import retrofit2.Retrofit
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.Charset
import javax.inject.Inject

class ApiValidityTest {
    @Mock
    @Inject
    lateinit var mRetrofit: Retrofit

    private lateinit var jsonWeatherSet:JSONWeatherSet

    @Test
    @Throws(Exception::class)
    fun testAvailability() {
        val connection =
            URL("https://api.openweathermap.org/data/2.5/weather?units=metric&appid=e7c7c90365190f0a6c0329e1bf041087&q=dubai,uae").openConnection()
        val response = connection.getInputStream()

        val buffer = StringBuffer()
        BufferedReader(
            InputStreamReader(
                response,
                Charset.defaultCharset()
            )
        ).use { reader ->
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                buffer.append(line)
            }
        }

        assert(buffer.isNotEmpty())
    }

//    @Test
//    fun getWeather() {
//        val weatherApi = mRetrofit!!.create(WeatherApi::class.java)
//        val result = weatherApi.getWeatherDetail("209801", Constant.METRIC, Constant.API_KEY)
//        Assert.assertNotNull(result)
//        // val result = WeatherRepositoryImpl(AppRetrofit.getInstance().create(WeatherService::class.java)).getWeatherDetails(Locale.getDefault().country)
//
//    }
}