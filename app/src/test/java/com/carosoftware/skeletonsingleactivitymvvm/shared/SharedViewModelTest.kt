package com.carosoftware.skeletonsingleactivitymvvm.shared

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.carosoftware.skeletonsingleactivitymvvm.framework.SharedViewModel
import com.carosoftware.skeletonsingleactivitymvvm.getOrAwaitValue
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedViewModelTest {

    // Subject under test
    private lateinit var sharedViewModel: SharedViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        sharedViewModel = SharedViewModel()
    }

    @Test
    fun updateBoughtAds() {
        sharedViewModel.updateBoughtAds()
        val adsBought = sharedViewModel.adsBought.getOrAwaitValue()
        assertThat(adsBought, CoreMatchers.`is`(true))

    }
}