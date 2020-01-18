package com.ashish.weather.util

import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import android.net.ConnectivityManager
import com.ashish.weather.view.MainActivity
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.util.*


class UtilitiesTest {
    @Mock
    private lateinit var mainActivity: MainActivity
    lateinit var context: Context

    @Mock
    private lateinit var geocoder: Geocoder


    @Before
    fun setUp() {
        mainActivity = Mockito.spy(MainActivity::class.java)
        context = mock(Context::class.java)
        geocoder= Geocoder(context, Locale.getDefault())
    }



    @Test
    fun isNetworkAvailable() {
        val manager = mock(ConnectivityManager::class.java)

        Mockito.`when`(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(manager)

        assertNotNull(Utilities.isNetworkAvailable(context))
    }

    @Test
    fun isGPSkOn() {
        val manager = mock(LocationManager::class.java)

        Mockito.`when`(context.getSystemService(Context.LOCATION_SERVICE)).thenReturn(manager)
        Mockito.`when`(context.getSystemService(LocationManager.GPS_PROVIDER)).thenReturn(manager)

        assertNotNull(Utilities.isLocationEnabled(context))
    }

    @Test
    fun testAddress() {

//        assertNotNull(Utilities.getPinCode(context,26.539345,80.487823))
    }
}