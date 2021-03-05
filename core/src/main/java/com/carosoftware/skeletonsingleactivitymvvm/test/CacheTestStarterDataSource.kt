package com.carosoftware.skeletonsingleactivitymvvm.test

import com.carosoftware.skeletonsingleactivitymvvm.data.StarterDataSource
import com.carosoftware.skeletonsingleactivitymvvm.domain.StarterModel
import com.carosoftware.skeletonsingleactivitymvvm.domain.result.SkeletonResult
import com.carosoftware.skeletonsingleactivitymvvm.test.mockData.StarterMock
import com.carosoftware.skeletonsingleactivitymvvm.test.mockData.StarterMockType
import io.reactivex.Single

class InMemoryStartersDataSource private constructor(): StarterDataSource {

    companion object {
        fun newInstance(): InMemoryStartersDataSource {
            return InMemoryStartersDataSource()
        }
    }

    private val listOfStarters = mutableListOf<StarterModel>()

    init {
        /* comment this out to simulate NO data in the local cache if you later add the remote data source */
        StarterMock.listOfStarters(StarterMockType.LOCAL).forEach {
            listOfStarters.add(it)
        }
    }

    override suspend fun add(starterModel: StarterModel) {
        listOfStarters.add(starterModel)
    }

    override suspend fun getAllStarters(): SkeletonResult<List<StarterModel>> {
        return SkeletonResult.Success(listOfStarters)
    }

    override fun getAllStartersObservable(): Single<SkeletonResult<List<StarterModel>>> {
        return Single.create { emitter ->
            emitter.onSuccess(SkeletonResult.Success(listOfStarters))
        }
    }

    override suspend fun remove(starterModel: StarterModel) {
        listOfStarters.remove(starterModel)
    }
}