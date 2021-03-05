package com.carosoftware.skeletonsingleactivitymvvm.presentation.starting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carosoftware.skeletonsingleactivitymvvm.domain.StarterModel
import com.carosoftware.skeletonsingleactivitymvvm.domain.result.SkeletonResult
import com.carosoftware.skeletonsingleactivitymvvm.framework.interactors.Interactors
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class StarterViewModel(): ViewModel(), KoinComponent {

    private val _starters: MutableLiveData<SkeletonResult<List<StarterModel>>> = MutableLiveData(SkeletonResult.Loading)

    val starters: LiveData<SkeletonResult<List<StarterModel>>> = _starters

    private val interactors: Interactors by inject()

    fun getStarters() {
        GlobalScope.launch {
            _starters.postValue(interactors.getStarters.get())
        }
    }

    fun getStartersRx() {
        interactors.getStarters.getObservable()
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onSuccess = {result ->
                            _starters.postValue(result)
                        },

                        onError = {
                            _starters.postValue(SkeletonResult.Error(Exception(it.message)))
                        }
                )
    }
}