package com.ashish.weather.util

import com.google.gson.Gson
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks

abstract class TestUtilsTest {

    @InjectMocks
    lateinit var mGson: Gson

    @Before
    @Throws(Exception::class)
    fun setUp() {
        TestUtils.loadJson<Any>("mock/WeatherMockDubai.json")

    }

    @Test
    fun dubaiDummyJson() {
        Assert.assertNotNull(mGson)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }

    @Test
    fun loadJson() {
    }

    @Test
    fun loadTrackLeadsJson() {
    }
}