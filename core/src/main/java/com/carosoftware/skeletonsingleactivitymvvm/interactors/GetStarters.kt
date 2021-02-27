package com.carosoftware.skeletonsingleactivitymvvm.interactors

import com.carosoftware.skeletonsingleactivitymvvm.data.StarterRepository

class GetStarters (private val starterRepository: StarterRepository) {
    suspend operator fun invoke() = starterRepository.getAllStarters()

}