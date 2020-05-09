package com.cazimir.skeletonsingleactivitymvvm.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private var _adsBought: MutableLiveData<Boolean> = MutableLiveData()
    val adsBought: LiveData<Boolean> = _adsBought
    private var _proBought: MutableLiveData<Boolean> = MutableLiveData()
    val proBought: LiveData<Boolean> = _proBought

    fun updateBoughtAds() {
        _adsBought.value = true
    }

    fun updateBoughtPro() {
        _proBought.value = true
    }
}