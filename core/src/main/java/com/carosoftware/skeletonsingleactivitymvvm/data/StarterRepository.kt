package com.carosoftware.skeletonsingleactivitymvvm.data

import com.carosoftware.skeletonsingleactivitymvvm.domain.StarterModel

class StarterRepository(
    private val starterDataSource: StarterDataSource
) {
    suspend fun add(starterModel: StarterModel) {
        starterDataSource.add(starterModel)
    }

    suspend fun getAllStarters() = starterDataSource.getAllStarters()

    suspend fun remove(starterModel: StarterModel) = starterDataSource.remove(starterModel)
}