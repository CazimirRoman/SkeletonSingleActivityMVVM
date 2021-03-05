package com.carosoftware.skeletonsingleactivitymvvm.presentation.starting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.carosoftware.skeletonsingleactivitymvvm.domain.StarterModel
import com.carosoftware.skeletonsingleactivitymvvm.domain.result.SkeletonResult
import com.carosoftware.skeletonsingleactivitymvvm.framework.di.testModule
import com.carosoftware.skeletonsingleactivitymvvm.framework.interactors.Interactors
import com.carosoftware.skeletonsingleactivitymvvm.getOrAwaitValue
import com.carosoftware.skeletonsingleactivitymvvm.test.mockData.StarterMock
import com.carosoftware.skeletonsingleactivitymvvm.test.mockData.StarterMockType
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StarterViewModelTest: KoinTest {

    private lateinit var sut: StarterViewModel
    private val interactors: Interactors by inject()

    @Mock
    private lateinit var observer: Observer<List<StarterModel>>

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin(listOf(testModule))
        sut = StarterViewModel()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `check initial value is loading`() {
        assertEquals(sut.starters.getOrAwaitValue(), SkeletonResult.Loading)
    }

    @Test
    fun `check that loadStarters populates the liveData correctly`() {
        sut.getStarters()
        assertEquals(sut.starters.getOrAwaitValue(), SkeletonResult.Success(StarterMock.listOfStarters(StarterMockType.LOCAL)))
    }

    @Test
    fun `check that loadStartersRx populates the liveData correctly`() {
        sut.getStartersRx()
        assertEquals(sut.starters.getOrAwaitValue(), SkeletonResult.Success(StarterMock.listOfStarters(StarterMockType.LOCAL)))
    }
}