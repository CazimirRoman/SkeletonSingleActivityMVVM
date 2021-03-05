package com.carosoftware.skeletonsingleactivitymvvm.data

import com.carosoftware.skeletonsingleactivitymvvm.domain.StarterModel
import com.carosoftware.skeletonsingleactivitymvvm.domain.result.SkeletonResult
import io.reactivex.Single

interface StarterDataSource {
    suspend fun add(starterModel: StarterModel)
    suspend fun getAllStarters(): SkeletonResult<List<StarterModel>>
    fun getAllStartersObservable(): Single<SkeletonResult<List<StarterModel>>>
    suspend fun remove(starterModel: StarterModel)
}