package com.ashish.weather.util

import android.content.Context
import android.net.ConnectivityManager
import com.ashish.weather.view.MainActivity
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock


class UtilitiesTest {
    @Mock
    private lateinit var mainActivity: MainActivity
 lateinit var    context: Context

    @Before
    fun setUp(){
        mainActivity=Mockito.spy(MainActivity::class.java)
         context = mock(Context::class.java)
    }

    @Test
    fun isNetworkAvailable() {

    }

    @Test
    fun isGpsOn() {
        val manager = mock(ConnectivityManager::class.java)

        Mockito.`when`(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(manager)

        assertNotNull(Utilities.isNetworkAvailable(context))
    }
}