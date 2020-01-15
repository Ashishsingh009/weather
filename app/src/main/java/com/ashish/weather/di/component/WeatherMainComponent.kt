package com.ashish.weather.di.component

import com.ashish.weather.di.annotation.PerActivity
import com.ashish.weather.view.MainActivity
import com.ashish.weather.viewmodel.MainViewModel
import dagger.Component

/**
 * Interface for dependency injection in MainActivity, MainViewModel
 * @author Ashish Singh
 * @date :10-01-2020
 */
@PerActivity
@Component(dependencies = [AppComponent::class])
interface WeatherMainComponent {
    /**
     * Method to provide dependency injection for MainActivity.
     * @param activity Dependent instance to which dagger will provide dependency.
     */
    fun inject(activity: MainActivity?)

    /**
     * Method to provide dependency injection for MainViewModel.
     * @param MainViewModel Dependent instance to which dagger will provide dependency.
     */
    fun inject(mainViewModel: MainViewModel?)



}