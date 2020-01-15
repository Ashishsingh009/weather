package com.ashish.weather.di.annotation

import javax.inject.Scope

/**
 * TO bind the activity at runtime in module.
 * @AUTHOR-ASHISHSINGH
 *
 */
@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity