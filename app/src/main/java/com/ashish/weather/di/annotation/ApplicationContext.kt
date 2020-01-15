package com.ashish.weather.di.annotation

import javax.inject.Qualifier

/**
 * TO get the Application context at runtime.
 * @author Ashish Singh
 * Date-10-01-2020
 */
@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext