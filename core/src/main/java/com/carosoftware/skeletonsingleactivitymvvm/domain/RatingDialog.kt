package com.carosoftware.skeletonsingleactivitymvvm.domain

data class RatingDialog(
    val later: Int = 0,
    val timesOpened: Int = 0,
    val never: Boolean = false,
    val show: Boolean = timesOpened == RATING_INTERVAL + later && !never
)