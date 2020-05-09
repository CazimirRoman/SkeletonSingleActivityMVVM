package com.cazimir.skeletonsingleactivitymvvm

/* Used to communicate from fragments to hosting activity */
interface IMainActivityCallback {
    fun hideSplash()
    fun removeAds()
}
