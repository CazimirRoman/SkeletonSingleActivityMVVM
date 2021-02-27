package com.carosoftware.skeletonsingleactivitymvvm.framework.interactors

import com.carosoftware.skeletonsingleactivitymvvm.interactors.AddStarter
import com.carosoftware.skeletonsingleactivitymvvm.interactors.GetStarters

data class Interactors(
    val addStarter: AddStarter,
    val getStarters: GetStarters
)