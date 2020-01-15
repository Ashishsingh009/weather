package com.ashish.weather.di.component

import android.content.Context
import com.ashish.weather.di.annotation.ApplicationContext
import com.ashish.weather.di.module.AppModule
import com.ashish.weather.di.module.NetworkModule
import com.google.gson.Gson
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 *AppComponent class contains the dependency of App module and n/w module.
 *  Class for Dagger injection. Methods with in the
 * interface is Globally access.
 * @author Ashish Singh
 * @date 10-01-2020
 */
@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    @ApplicationContext
    fun getContext(): Context?

    fun gson(): Gson?
    fun getRetrofitClient(): Retrofit?
}