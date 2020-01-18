package com.ashish.weather.util

import com.ashish.weather.model.ChartData
import com.ashish.weather.model.JSONWeatherSet
import com.google.gson.Gson
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks

class TestUtilsTest {

    @InjectMocks
    lateinit var mGson: Gson

    private lateinit var jsonWeatherSet: JSONWeatherSet

    private lateinit var chartData: ChartData

    @Before
    @Throws(Exception::class)
    fun setUp() {
        jsonWeatherSet = TestUtils.loadJson<Any>("mock/WeatherMockDubai.json") as JSONWeatherSet
        chartData= ChartData("2020-01-18","20")

    }


    @Test
    fun dubaiDummyJson() {
        Assert.assertNotNull(jsonWeatherSet)
    }

    @Test
    fun tempNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.main.temp, 12.00)
    }

    @Test
    fun tempMin() {
        Assert.assertEquals(jsonWeatherSet.main.temp_min, 20.00, 0.0)
    }

    @Test
    fun baseNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.base, 12.00)
    }

    @Test
    fun baseMin() {
        Assert.assertEquals(jsonWeatherSet.main.temp_min, 20.00, 0.0)
    }


    @Test
    fun feelsLikeNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.main.feels_like, 12.00)
    }

    @Test
    fun feelsLikeMin() {
        Assert.assertEquals(jsonWeatherSet.main.feels_like, 16.39, 0.0)
    }

    @Test
    fun tempMax() {
        Assert.assertEquals(jsonWeatherSet.main.temp_max, 21.00, 0.0)
    }

    @Test
    fun pressure() {
        Assert.assertEquals(jsonWeatherSet.main.pressure, 1016)
    }

    @Test
    fun humidity() {
        Assert.assertEquals(jsonWeatherSet.main.humidity, 49)
    }

    @Test
    fun visibility() {
        Assert.assertEquals(jsonWeatherSet.visibility, 10000)
    }

    @Test
    fun visibilityNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.visibility, 99999)
    }

    @Test
    fun windSpeed() {
        Assert.assertEquals(jsonWeatherSet.wind.speed, 5.7, 0.0)
    }

    @Test
    fun windSpeedNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.wind.speed, 5)
    }

    @Test
    fun windDeg() {
        Assert.assertEquals(jsonWeatherSet.wind.deg, 260)
    }

    @Test
    fun windDegNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.wind.deg, 0)
    }

    @Test
    fun coordinateNotNull() {
        Assert.assertNotNull(jsonWeatherSet.coord)
    }

    @Test
    fun latitude() {
        Assert.assertEquals(jsonWeatherSet.coord.lat, 25.26, 0.0)
    }

    @Test
    fun latitudeNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.coord.lat, 12)
    }


    @Test
    fun longitudeNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.coord.lon, 2323)
    }

    @Test
    fun longitude() {
        Assert.assertEquals(jsonWeatherSet.coord.lon, 55.3, 0.0)
    }

    @Test
    fun getWeather() {
        Assert.assertNotNull(jsonWeatherSet.weather)
    }

    @Test
    fun getWeatherId() {
        Assert.assertEquals(jsonWeatherSet.weather[0].id, 803)
    }

    @Test
    fun getMainNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.weather[0].main, "demo")
    }

    @Test
    fun getMain() {
        Assert.assertEquals(jsonWeatherSet.weather[0].main, "Clouds")
    }

    @Test
    fun getWeatherIdNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.weather[0].id, 1)
    }


    @Test
    fun getDescriptionNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.weather[0].description, "description test")
    }

    @Test
    fun getDescription() {
        Assert.assertEquals(jsonWeatherSet.weather[0].description, "broken clouds")
    }

    @Test
    fun getIconNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.weather[0].icon, "mcdkscn873874")
    }

    @Test
    fun getIcon() {
        Assert.assertEquals(jsonWeatherSet.weather[0].icon, "04d")
    }

    @Test
    fun getSystemId() {
        Assert.assertEquals(jsonWeatherSet.sys.id, 7537)
    }
    @Test
    fun getSystemIdNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.sys.id, 12312313)
    }

    @Test
    fun getCountryCode() {
        Assert.assertEquals(jsonWeatherSet.sys.country, "AE")
    }
    @Test
    fun getCountryCodeNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.sys.id, "US")
    }
    @Test
    fun getType() {
        Assert.assertEquals(jsonWeatherSet.sys.type, 1)
    }
    @Test
    fun getTypeNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.sys.id, 34)
    }

    @Test
    fun getSunrise() {
        Assert.assertEquals(jsonWeatherSet.sys.sunrise, 1578971161)
    }
    @Test
    fun getSunriseNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.sys.sunrise, 1231423424)
    }

    @Test
    fun getSunset() {
        Assert.assertEquals(jsonWeatherSet.sys.sunset, 1579009721)
    }
    @Test
    fun getSunsetNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.sys.sunset, 427346527638)
    }


    @Test
    fun getClouds() {
        Assert.assertNotNull(jsonWeatherSet.clouds)
    }
    @Test
    fun getCloudAllNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.clouds.all, 7512)
    }

    @Test
    fun getCloudAl() {
        Assert.assertEquals(jsonWeatherSet.clouds.all, 75)
    }

    @Test
    fun getDateNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.dt, 7512)
    }

    @Test
    fun getDate() {
        Assert.assertEquals(jsonWeatherSet.dt, 1578999429)
    }

    @Test
    fun getTimeZoneNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.timezone, 7512)
    }

    @Test
    fun getTimeZone() {
        Assert.assertEquals(jsonWeatherSet.timezone, 14400)
    }

    @Test
    fun getNameNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.name, "Test Name")
    }

    @Test
    fun getName() {
        Assert.assertEquals(jsonWeatherSet.name, "Dubai")
    }

    @Test
    fun getIdNotNull() {
        Assert.assertNotNull(jsonWeatherSet.id)
    }

    @Test
    fun getIdNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.id,879869)
    }

    @Test
    fun getId() {
        Assert.assertEquals(jsonWeatherSet.id, 292223)
    }

    @Test
    fun getCodNotEqual() {
        Assert.assertNotEquals(jsonWeatherSet.cod,879869)
    }

    @Test
    fun getCod() {
        Assert.assertEquals(jsonWeatherSet.cod, 200)
    }

    @Test
    fun getChartDataNotNull() {
        Assert.assertNotNull(chartData)
    }

    @Test
    fun getChartData() {
        Assert.assertEquals(chartData.temp, "20")
    }

    @Test
    fun getChartDate() {
        Assert.assertEquals(chartData.date, "2020-01-18")
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {

    }

}