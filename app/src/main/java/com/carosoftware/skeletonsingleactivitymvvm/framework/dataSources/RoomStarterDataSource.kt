package com.carosoftware.skeletonsingleactivitymvvm.framework.dataSources

import android.content.Context
import com.carosoftware.skeletonsingleactivitymvvm.data.StarterDataSource
import com.carosoftware.skeletonsingleactivitymvvm.domain.StarterModel
import com.carosoftware.skeletonsingleactivitymvvm.domain.result.SkeletonResult
import com.carosoftware.skeletonsingleactivitymvvm.framework.db.StarterDatabase
import com.carosoftware.skeletonsingleactivitymvvm.framework.db.StarterEntity
import io.reactivex.Single

class RoomStarterDataSource(context: Context): StarterDataSource {

    private val starterDao = StarterDatabase.getInstance(context).starterDao()

    override suspend fun add(starterModel: StarterModel) {
        starterDao.addStarter(StarterEntity(text = starterModel.text, size = starterModel.size))
    }

    override suspend fun getAllStarters(): SkeletonResult<List<StarterModel>> {

        val list: List<StarterModel> = starterDao.getAllStarters().map {
            StarterModel(text = it.text, size = it.size)
        }

        return SkeletonResult.Success(list)
    }

    override fun getAllStartersObservable(): Single<SkeletonResult<List<StarterModel>>> {
        val list: List<StarterModel> = starterDao.getAllStarters().map {
            StarterModel(text = it.text, size = it.size)
        }

        return Single.create { emitter ->
            emitter.onSuccess(SkeletonResult.Success(list))
        }
    }

    override suspend fun remove(starterModel: StarterModel) {
            starterDao.removeStarter(StarterEntity(text = starterModel.text, size = starterModel.size))
        }
    }