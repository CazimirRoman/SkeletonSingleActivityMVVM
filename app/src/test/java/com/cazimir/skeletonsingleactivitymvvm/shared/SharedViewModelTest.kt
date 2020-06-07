package com.cazimir.skeletonsingleactivitymvvm.shared

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cazimir.skeletonsingleactivitymvvm.getOrAwaitValue
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedViewModelTest {

    @Before
    fun setUp() {
    }

    @Test
    fun updateBoughtAds() {
        val sharedViewModel = SharedViewModel()
        sharedViewModel.updateBoughtAds()
        val adsBought = sharedViewModel.adsBought.getOrAwaitValue()
        assertThat(adsBought, CoreMatchers.`is`(true))

    }
}