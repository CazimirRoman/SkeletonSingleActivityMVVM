package com.carosoftware.skeletonsingleactivitymvvm.presentation.starting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carosoftware.skeletonsingleactivitymvvm.domain.StarterModel
import com.carosoftware.skeletonsingleactivitymvvm.framework.interactors.Interactors
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class StarterViewModel(): ViewModel(), KoinComponent {

    private val _starters: MutableLiveData<List<StarterModel>> = MutableLiveData()

    val starters: LiveData<List<StarterModel>> = _starters

    private val interactors: Interactors by inject()

    fun getStarters() {
        GlobalScope.launch {
            _starters.postValue(interactors.getStarters())
        }
    }
}