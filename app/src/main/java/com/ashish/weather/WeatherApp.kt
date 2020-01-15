package com.ashish.weather

import android.app.Application
import com.ashish.weather.di.component.AppComponent
import com.ashish.weather.di.component.DaggerAppComponent
import com.ashish.weather.di.module.AppModule
import com.ashish.weather.di.module.NetworkModule
import com.ashish.weather.util.Constant

class WeatherApp : Application() {
    private var mAppComponent: AppComponent? = null

    companion object {
        var mApp: WeatherApp? = null

        /**
         * Method to return the instance of this application.
         * @return Instance of this class;
         */

        fun getInstance(): WeatherApp? {
            return mApp
        }
    }

    override fun onCreate() {
        super.onCreate()
        mApp = this
        initializeInjector()
    }

    private fun initializeInjector() {
        mAppComponent = DaggerAppComponent.builder()
            .appModule(AppModule())
            .networkModule(NetworkModule(Constant.BASE_URL))
            .build()
    }




    fun getAppComponent(): AppComponent? {
        return mAppComponent
    }
}