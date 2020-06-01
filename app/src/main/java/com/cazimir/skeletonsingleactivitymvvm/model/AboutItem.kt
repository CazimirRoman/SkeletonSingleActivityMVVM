package com.cazimir.skeletonsingleactivitymvvm.model

class AboutItem(val name: AboutItemType, val icon: Int)

sealed class AboutItemType(val textToDisplay: String) {

    class AboutTheApp(val text: String): AboutItemType(text)
    class SendFeedback(val text: String) : AboutItemType(text)
    class RemoveAds(val text: String) : AboutItemType(text)
    class Share(val text: String) : AboutItemType(text)
    class PrivacyPolicy(val text: String) : AboutItemType(text)
    class RateApp(val text: String) : AboutItemType(text)
    class MoreApps(val text: String) : AboutItemType(text)
}
