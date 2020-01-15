package com.ashish.weather.view

import com.ashish.weather.databinding.ActivityMainBinding
import com.ashish.weather.model.JSONWeatherSet
import com.ashish.weather.util.TestUtils
import com.ashish.weather.viewmodel.MainViewModel
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import javax.inject.Inject

class MainActivityTest {

    private lateinit var jsonWeatherSet: JSONWeatherSet
    @Inject
    var mGson: Gson? = null

    @Mock
    private lateinit var binding: ActivityMainBinding
    @Mock
    lateinit var mainActivity: MainActivity
    @Mock
    private lateinit var viewModel: MainViewModel


    @Before
    fun setUp() {
        mainActivity = Mockito.spy(MainActivity::class.java)
        jsonWeatherSet= TestUtils.loadJson("mock/WeatherMockDubai.json")

    }


    @Test
    fun getJsonTest(){
        Assert.assertNotEquals(jsonWeatherSet.main.temp,110.90)
    }
    @Test
    fun onItemCount() {
        Assert.assertTrue(jsonWeatherSet.main.temp > 0)
    }

    @Test
    fun getSize() {
        Assert.assertTrue(jsonWeatherSet.weather.size== 1)
    }
    @Test
    fun onCreate() {
    }

    @Test
    fun onRefresh() {
        Assert.assertNotNull(binding.swipeRefresh)
    }

    @Test
    fun onClick() {
    }
}