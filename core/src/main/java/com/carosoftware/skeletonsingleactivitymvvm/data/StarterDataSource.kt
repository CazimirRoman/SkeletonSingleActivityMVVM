package com.carosoftware.skeletonsingleactivitymvvm.data

import com.carosoftware.skeletonsingleactivitymvvm.domain.Starter

interface StarterDataSource {
    suspend fun add(starter: Starter)
    suspend fun getAllStarters(): List<Starter>
    suspend fun remove(starter: Starter)
}