package com.carosoftware.skeletonsingleactivitymvvm.model

import com.carosoftware.skeletonsingleactivitymvvm.MainActivity

data class RatingDialog(
    val later: Int = 0,
    val timesOpened: Int = 0,
    val never: Boolean = false,
    val show: Boolean = timesOpened == MainActivity.RATING_INTERVAL + later && !never
)