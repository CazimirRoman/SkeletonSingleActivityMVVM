package com.cazimir.skeletonsingleactivitymvvm.analytics

import android.os.Bundle

class AnalyticsEvents {

    // example usage: activityCallback.logAnalyticsEvent(soundClicked(sound).first.name, soundClicked(sound).second)

    companion object {
        //this static method will return a PAIR with the event key and the bundle that need to be sent to Firebase Analytics
        //yourObject.someProperty is meant by yourObject.toString()
        fun objectClicked(yourObject: Any): Pair<String, Bundle> {
            val bundle = Bundle()
            bundle.putString(BundleKey.YYYYY_NAME.bundleKey, yourObject.toString())
            return Pair(EventKey.XXXXXX_CLICKED.name, bundle)
        }
    }
}

// name of the event (clicked, shared, used certain feature like send feedback) etc
enum class EventKey {
    XXXXXX_CLICKED
}

enum class BundleKey(val bundleKey: String) {
    // keys for the bundle for custom data that you want to log
    YYYYY_NAME("yyyyy_name")
}