package com.ashish.weather.view

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.unregisterIdlingResources
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.ashish.weather.R
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityLargeTest {


    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkIfTemperatureIsDisplayedFirst() {
        onView(withId(R.id.temperature)).check(matches(isDisplayed()))
    }

    @Test
    fun checkIfTextViewIsNotDisplayedOnLaunch() {
        onView(withId(R.id.datetime)).check(matches((isDisplayed())))
    }

    @Test
    fun checkIfDatetimeTextViewIsNotDisplayedOnLaunch() {
        onView(withId(R.id.detailsTxtView)).check(matches((isDisplayed())))
    }

    @Test
    fun waitForNetworkCallToCompleteAndCheckViews() {
        val mainActivityIdlingResource = mActivityTestRule.activity.countingIdlingResource
        IdlingRegistry.getInstance().register(mainActivityIdlingResource)
        onView(withId(R.id.temperature)).check(matches(isDisplayed()))
        onView(withId(R.id.datetime)).check(matches(isDisplayed()))
        onView(withId(R.id.detailsTxtView)).check(matches(isDisplayed()))
        IdlingRegistry.getInstance().unregister(mainActivityIdlingResource)
    }


    @After
    fun tearDown() {
        val idlingResourceList =
            Espresso.getIdlingResources()
        if (idlingResourceList != null) {
            for (i in idlingResourceList.indices) {
                unregisterIdlingResources(idlingResourceList[i])
            }
        }
    }


}