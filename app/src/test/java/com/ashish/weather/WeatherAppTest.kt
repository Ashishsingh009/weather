package com.ashish.weather

import android.content.Context
import com.ashish.weather.di.component.AppComponent
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class WeatherAppTest {
    @Mock
    private lateinit var mAppComponent: AppComponent

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mock(Context::class.java)
    }

    @Test
    fun getInstance() {

        Assert.assertEquals(mAppComponent,mAppComponent);
    }
}