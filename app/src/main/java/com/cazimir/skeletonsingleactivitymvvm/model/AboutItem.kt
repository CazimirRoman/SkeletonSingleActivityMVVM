package com.cazimir.skeletonsingleactivitymvvm.model

class AboutItem(val name: MenuItemType, val icon: Int)

enum class MenuItemType(val itemName: String) {
    SEND_FEEDBACK("Send us feedback"),
    REMOVE_ADS("Remove Ads"),
    SHARE("Share"),
    PRIVACY_POLICY("Privacy policy"),
    RATE_APP("Rate app"),
    MORE_APPS("More apps")
}
