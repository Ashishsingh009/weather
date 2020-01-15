package com.ashish.weather.di.module

import android.app.Application
import android.content.Context
import com.ashish.weather.WeatherApp
import com.ashish.weather.di.annotation.ApplicationContext
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

   lateinit var app:WeatherApp

    fun AppModule(app: WeatherApp) {
        this.app = app
    }

    @Provides
    @Singleton
    fun provideApplication(): Application? {
        return app
    }

    @Provides
    @ApplicationContext
    fun provideContext(): Context? {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideGson(): Gson? {
        return Gson()
    }
}