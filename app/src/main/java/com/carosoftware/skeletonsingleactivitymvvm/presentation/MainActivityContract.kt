package com.carosoftware.skeletonsingleactivitymvvm.presentation

import android.os.Bundle

/* Used to communicate from fragments to hosting activity */
interface MainActivityContract {
    fun hideSplash()
    fun removeAds()
    fun logAnalyticsEvent(event: String, bundle: Bundle)
}
