package com.ashish.weather.view

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.ashish.weather.R
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class WeatherDetailActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(WeatherDetailActivity::class.java)

    @Test
    fun checkIfAddressContainerIsDisplayedFirst() {
        Espresso.onView(ViewMatchers.withId(R.id.addressContainer))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkIfAddressIsDisplayedFirst() {
        Espresso.onView(ViewMatchers.withId(R.id.address))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkIfLastUpdateIsDisplayedFirst() {
        Espresso.onView(ViewMatchers.withId(R.id.updated_at))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkIfOverviewLayoutContainerIsDisplayedFirst() {
        Espresso.onView(ViewMatchers.withId(R.id.overviewContainer))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun checkIfStatusIsDisplayedFirst() {
        Espresso.onView(ViewMatchers.withId(R.id.status))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun checkIfTempContainerIsDisplayedFirst() {
        Espresso.onView(ViewMatchers.withId(R.id.temp))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }@Test
    fun checkIfTempMaxContainerIsDisplayedFirst() {
        Espresso.onView(ViewMatchers.withId(R.id.temp_max))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun checkIfTempMinContainerIsDisplayedFirst() {
        Espresso.onView(ViewMatchers.withId(R.id.temp_min))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun checkIfDetailsContainerLayoutContainerIsDisplayedFirst() {
        Espresso.onView(ViewMatchers.withId(R.id.detailsContainer))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun waitForDataToCompleteAndCheckViews(){
        val mainActivityIdlingResource = mActivityTestRule.activity.countingIdlingResource
        IdlingRegistry.getInstance().register(mainActivityIdlingResource)
        Espresso.onView(ViewMatchers.withId(R.id.status))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.address))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.updated_at))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.temp))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.temp_max))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.temp_min))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        IdlingRegistry.getInstance().unregister(mainActivityIdlingResource)
    }

    @After
    fun tearDown() {
        val idlingResourceList =
            Espresso.getIdlingResources()
        if (idlingResourceList != null) {
            for (i in idlingResourceList.indices) {
                Espresso.unregisterIdlingResources(idlingResourceList[i])
            }
        }
    }
}