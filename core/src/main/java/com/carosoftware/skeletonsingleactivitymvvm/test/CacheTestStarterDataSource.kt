package com.carosoftware.skeletonsingleactivitymvvm.test

import com.carosoftware.skeletonsingleactivitymvvm.data.StarterDataSource
import com.carosoftware.skeletonsingleactivitymvvm.domain.StarterModel
import com.carosoftware.skeletonsingleactivitymvvm.test.mockData.StarterMock
import com.carosoftware.skeletonsingleactivitymvvm.test.mockData.StarterMockType

class CacheTestPostDataSource private constructor(): StarterDataSource {

    companion object {
        fun newInstance(): CacheTestPostDataSource {
            return CacheTestPostDataSource()
        }
    }

    private val listOfStarters = mutableListOf<StarterModel>()

    init {
        /* commented this out to simulate NO data in the local cache if you later add the remote data source */
        StarterMock.listOfStarters(StarterMockType.LOCAL).forEach {
            listOfStarters.add(it)
        }
    }

    override suspend fun add(starterModel: StarterModel) {
        listOfStarters.add(starterModel)
    }

    override suspend fun getAllStarters(): List<StarterModel> {
        return listOfStarters
    }

    override suspend fun remove(starterModel: StarterModel) {
        listOfStarters.remove(starterModel)
    }
}