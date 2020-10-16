package com.carosoftware.skeletonsingleactivitymvvm

import android.os.Bundle

/* Used to communicate from fragments to hosting activity */
interface IMainActivityCallback {
    fun hideSplash()
    fun removeAds()
    fun logAnalyticsEvent(event: String, bundle: Bundle)
}
