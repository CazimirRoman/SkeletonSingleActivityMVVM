package com.carosoftware.skeletonsingleactivitymvvm.base

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.carosoftware.skeletonsingleactivitymvvm.BuildConfig
import com.carosoftware.skeletonsingleactivitymvvm.util.SharedPreferencesUtil
import com.google.firebase.analytics.FirebaseAnalytics
import timber.log.Timber

class BaseApplication : Application() {

    companion object {
        // TODO: change type of channels
        const val XX_CHANNEL = "XX_CHANNEL"
        const val YY_CHANNEL = "YY_CHANNEL"
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        SharedPreferencesUtil.with(this)
        createNotificationChannels()
        setupFirebaseAnalytics()
    }

    private fun setupFirebaseAnalytics() {
        // TODO: Setup Google Firebase connection(Tools -> Firebase) before using this otherwise app measurement will be disabled
        FirebaseAnalytics.getInstance(this).setAnalyticsCollectionEnabled(!BuildConfig.DEBUG)
    }

    private fun createNotificationChannels() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val foregroundServiceChannel = NotificationChannel(
                XX_CHANNEL,
                "Change this!!",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val notificationChannel = NotificationChannel(
                YY_CHANNEL,
                "Notification channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            foregroundServiceChannel.importance = NotificationManager.IMPORTANCE_LOW
            notificationChannel.importance = NotificationManager.IMPORTANCE_HIGH
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannels(listOf(notificationChannel, foregroundServiceChannel))
        }
    }
}
