package com.cazimir.skeletonsingleactivitymvvm.model

import com.cazimir.skeletonsingleactivitymvvm.MainActivity

data class RatingDialog(
    val later: Int = 0,
    val timesOpened: Int = 0,
    val never: Boolean = false,
    val show: Boolean = timesOpened == MainActivity.RATING_INTERVAL + later && !never
)