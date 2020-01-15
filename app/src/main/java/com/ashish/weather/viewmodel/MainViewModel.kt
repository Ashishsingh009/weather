package com.ashish.weather.viewmodel

import android.app.Application
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ashish.weather.WeatherApp
import com.ashish.weather.apis.WeatherApi
import com.ashish.weather.di.component.DaggerWeatherMainComponent
import com.ashish.weather.model.JSONWeatherSet
import com.ashish.weather.util.Constant
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject



open class MainViewModel(application: Application) : AndroidViewModel(application) {
    @JvmField
    @Inject
    var mRetrofit: Retrofit? = null

    @JvmField
    @Inject
    var mGson: Gson? = null

    @JvmField
    var loading: MutableLiveData<Boolean>? = null
    @JvmField
    var apiError: MutableLiveData<Throwable>? = null

    private val TAG: String = MainViewModel::class.java.name

    @JvmField
    var pinCode:String ?=null

    @JvmField
    var weatherResponse: MutableLiveData<JSONWeatherSet?>? = null


    lateinit var jsonWeatherSet: JSONWeatherSet

    init {
        loading = MutableLiveData()
        apiError = MutableLiveData()
        weatherResponse = MutableLiveData()
        initializeInjector()
    }

    private fun initializeInjector() {
        DaggerWeatherMainComponent
            .builder()
            .appComponent(WeatherApp.mApp?.getAppComponent())
            .build()
            .inject(this)
    }

    val getWeatherData: LiveData<JSONWeatherSet?>?
        get() {
            val weatherApi = mRetrofit!!.create(WeatherApi::class.java)
            loading!!.value = true
            weatherApi.getWeatherDetail(pinCode, Constant.METRIC, Constant.API_KEY)
                ?.enqueue(object :
                    Callback<JSONWeatherSet?> {
                    override fun onResponse(
                        call: Call<JSONWeatherSet?>,
                        response: Response<JSONWeatherSet?>
                    ) {
                        Log.d(TAG, response.toString())
                        if (response.isSuccessful && response.code() == 200) {
                            val weatherData = response.body()
                            jsonWeatherSet = weatherData!!
                            weatherResponse!!.value = weatherData

                        }
                    }

                    override fun onFailure(call: Call<JSONWeatherSet?>, t: Throwable) {
                        loading!!.value = false
                        apiError!!.value = t
                    }
                })
            return weatherResponse
        }





}