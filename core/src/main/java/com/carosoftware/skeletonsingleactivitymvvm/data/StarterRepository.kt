package com.carosoftware.skeletonsingleactivitymvvm.data

import com.carosoftware.skeletonsingleactivitymvvm.domain.Starter

class StarterRepository(
    private val starterDataSource: StarterDataSource
) {
    suspend fun add(starter: Starter) {
        starterDataSource.add(starter)
    }

    suspend fun getAllStarters() = starterDataSource.getAllStarters()

    suspend fun remove(starter: Starter) = starterDataSource.remove(starter)
}