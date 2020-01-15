package com.ashish.weather.di.module

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule(baseUrl: String) {
    var mBaseUrl: String? = baseUrl

    @Provides
    @Singleton
    fun provideHttpInterceptor(): HttpLoggingInterceptor? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor?): OkHttpClient? {
        val protocols: MutableList<Protocol> = ArrayList()
        protocols.add(Protocol.HTTP_1_1)
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(1, TimeUnit.MINUTES)
        okHttpClient.readTimeout(1, TimeUnit.MINUTES)
        okHttpClient.protocols(protocols)
        if (httpLoggingInterceptor != null) {
            okHttpClient.addInterceptor(httpLoggingInterceptor)
        }
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient?): Retrofit? {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(mBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(okHttpClient!!)
            .build()
    }
}