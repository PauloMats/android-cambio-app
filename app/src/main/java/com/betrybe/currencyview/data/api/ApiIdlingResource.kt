package com.betrybe.currencyview.data.api

import androidx.test.espresso.idling.CountingIdlingResource

object ApiIdlingResource {
    private val countingIdlingResource = CountingIdlingResource("API")

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }

    val resource: CountingIdlingResource
        get() = countingIdlingResource
}