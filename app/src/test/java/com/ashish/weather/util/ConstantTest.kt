package com.ashish.weather.util

import org.junit.Assert
import org.junit.Test



class ConstantTest {

    @Test
    fun getBaseUrl(){
        Assert.assertEquals(Constant.BASE_URL,"https://api.openweathermap.org/")
    }

    @Test
    fun getApiKeyNotNull(){
        Assert.assertNotNull(Constant.BASE_URL)
    }

    @Test
    fun getCityNotNull(){
        Assert.assertNotNull(Constant.CITY_TEMP_API)
    }
    @Test
    fun getCityEqual(){
        Assert.assertEquals(Constant.CITY_TEMP_API,"data/2.5/weather?")
    }

    @Test
    fun getForecastEqual(){
        Assert.assertEquals(Constant.FORECAST_API,"data/2.5/forecast?")
    }


    @Test
    fun getTimeMilliSec() {
        Assert.assertNotEquals(Constant.getTimeMilliSec(1578986741),"2020-01-14 11:25")
    }

    @Test
    fun getTimeMilliSecIncorrect() {
        Assert.assertNotEquals(Constant.getTimeMilliSec(1578986741),"2020-01-14 13:25")
    }
    @Test
    fun getTimeMilliSecNotNull() {
        Assert.assertNotNull(Constant.getTimeMilliSec(1578986741))
    }

    @Test
    fun getHoursMilliSec() {
        Assert.assertNotEquals(Constant.getHoursMilliSec(1579001373000),"12:30:00 AM")
    }
    @Test
    fun getHoursMilliSecIncorrect() {
        Assert.assertNotEquals(Constant.getHoursMilliSec(15790013730000),"01:30:00 PM")
    }
}