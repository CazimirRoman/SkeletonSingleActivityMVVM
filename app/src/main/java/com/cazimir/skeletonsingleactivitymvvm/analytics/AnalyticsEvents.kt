package com.cazimir.skeletonsingleactivitymvvm.analytics

import android.os.Bundle

class AnalyticsEvents {

    // example usage: activityCallback.logAnalyticsEvent(soundClicked(sound).first.name, soundClicked(sound).second)

    companion object {
        //this static method will return a PAIR with the event key and the bundle that need to be sent to Firebase Analytics
        //yourObject.someProperty is meant by yourObject.toString()

        fun shareClicked(): Pair<String, Bundle> {
            val bundle = Bundle()
            bundle.putBoolean(BundleKey.SHARE_CLICKED.key, true)
            return Pair(EventKey.SHARE_CLICKED.name, bundle)
        }

        fun removeAdsClicked(): Pair<String, Bundle> {
            val bundle = Bundle()
            bundle.putBoolean(BundleKey.REMOVE_ADS_CLICKED.key, true)
            return Pair(EventKey.REMOVE_ADS_CLICKED.name, bundle)
        }

        fun privacyPolicyClicked(): Pair<String, Bundle> {
            val bundle = Bundle()
            bundle.putBoolean(BundleKey.PRIVACY_POLICY_CLICKED.key, true)
            return Pair(EventKey.PRIVACY_POLICY_CLICKED.name, bundle)
        }

        fun rateAppClicked(): Pair<String, Bundle> {
            val bundle = Bundle()
            bundle.putBoolean(BundleKey.RATE_APP_CLICKED.key, true)
            return Pair(EventKey.RATE_APP_CLICKED.name, bundle)
        }

        fun moreAppsClicked(): Pair<String, Bundle> {
            val bundle = Bundle()
            bundle.putBoolean(BundleKey.MORE_APPS_CLICKED.key, true)
            return Pair(EventKey.MORE_APPS_CLICKED.name, bundle)
        }
    }
}

// name of the event (clicked, shared, used certain feature like send feedback) etc
enum class EventKey {
    SHARE_CLICKED,
    REMOVE_ADS_CLICKED,
    PRIVACY_POLICY_CLICKED,
    RATE_APP_CLICKED,
    MORE_APPS_CLICKED,
}

enum class BundleKey(val key: String) {
    // keys for the bundle for custom data that you want to log
    SHARE_CLICKED("share_clicked"),
    REMOVE_ADS_CLICKED("remove_ads_clicked"),
    PRIVACY_POLICY_CLICKED("privacy_policy_clicked"),
    RATE_APP_CLICKED("rate_app_clicked"),
    MORE_APPS_CLICKED("more_apps_clicked")


}