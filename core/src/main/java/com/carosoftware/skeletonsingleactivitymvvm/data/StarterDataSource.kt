package com.carosoftware.skeletonsingleactivitymvvm.data

import com.carosoftware.skeletonsingleactivitymvvm.domain.StarterModel

interface StarterDataSource {
    suspend fun add(starterModel: StarterModel)
    suspend fun getAllStarters(): List<StarterModel>
    suspend fun remove(starterModel: StarterModel)
}