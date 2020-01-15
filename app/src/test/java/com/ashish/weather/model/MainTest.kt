package com.ashish.weather.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainTest {
    @Mock
    lateinit var main: Main
    private var temp: Double = 20.90
    private var tempMax: Double = 23.90
    private var tempMin: Double = 18.90
    private var feelsLike: Double = 13.90
    private var humidity: Int = 20
    private var pressure: Double = 2.0

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(main.temp).thenReturn(temp)
        Mockito.`when`(main.temp_max).thenReturn(tempMax)
        Mockito.`when`(main.temp_min).thenReturn(tempMin)
        Mockito.`when`(main.feels_like).thenReturn(feelsLike)
        Mockito.`when`(main.humidity).thenReturn(humidity)
        Mockito.`when`(main.pressure).thenReturn(pressure)
    }

    @Test
    fun getTemp() {
        Mockito.`when`(main.temp).thenReturn(temp)
        Assert.assertEquals(20.90, main.temp,0.0)
    }

    @Test
    fun getFeels_like() {
        Mockito.`when`(main.feels_like).thenReturn(feelsLike)
        Assert.assertEquals(13.90, main.feels_like,0.0)
    }

    @Test
    fun getTemp_min() {
        Mockito.`when`(main.temp_min).thenReturn(tempMin)
        Assert.assertEquals(18.90, main.feels_like,0.0)
    }

    @Test
    fun getTemp_max() {
        Mockito.`when`(main.temp_max).thenReturn(tempMax)
        Assert.assertEquals(23.90, main.temp_max,0.0)
    }

    @Test
    fun getPressure() {
        Mockito.`when`(main.pressure).thenReturn(pressure)
        Assert.assertEquals(2.0, main.pressure)
    }

    @Test
    fun getHumidity() {
        Mockito.`when`(main.humidity).thenReturn(humidity)
        Assert.assertEquals(20, main.humidity)
    }

    @Test
    operator fun component1() {
        Assert.assertNotEquals(temp, 27.65)
    }

    @Test
    operator fun component2() {
        Assert.assertNotEquals(tempMax, 97.65)
    }

    @Test
    operator fun component3() {
        Assert.assertNotEquals(tempMin, 0)
    }

    @Test
    operator fun component4() {
    }

    @Test
    operator fun component5() {
    }

    @Test
    operator fun component6() {
    }
}