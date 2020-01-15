package com.ashish.weather.util

import org.junit.Assert
import org.junit.Test



class ConstantTest {

    @Test
    fun getBaseUrl(){
        Assert.assertEquals(Constant.BASE_URL,"https://api.openweathermap.org/")
    }

    @Test
    fun getTimeMilliSec() {
        Assert.assertEquals(Constant.getTimeMilliSec(1578986741),"2020-01-14 11:25")
    }

    @Test
    fun getTimeMilliSecIncorrect() {
        Assert.assertNotEquals(Constant.getTimeMilliSec(1578986741),"2020-01-14 13:25")
    }
    @Test
    fun getHoursMilliSec() {
        Assert.assertEquals(Constant.getHoursMilliSec(1579001373000),"12:30:00 AM")
    }
    @Test
    fun getHoursMilliSecIncorrect() {
        Assert.assertNotEquals(Constant.getHoursMilliSec(1579001373000),"01:30:00 PM")
    }
}