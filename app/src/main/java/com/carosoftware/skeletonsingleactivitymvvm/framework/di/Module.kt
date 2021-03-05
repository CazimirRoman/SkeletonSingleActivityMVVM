package com.carosoftware.skeletonsingleactivitymvvm.framework.di

import com.carosoftware.skeletonsingleactivitymvvm.data.StarterRepository
import com.carosoftware.skeletonsingleactivitymvvm.framework.dataSources.RoomStarterDataSource
import com.carosoftware.skeletonsingleactivitymvvm.framework.interactors.Interactors
import com.carosoftware.skeletonsingleactivitymvvm.interactors.AddStarter
import com.carosoftware.skeletonsingleactivitymvvm.interactors.GetStarters
import com.carosoftware.skeletonsingleactivitymvvm.test.InMemoryStartersDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val mainModule = module {

    factory {
        AddStarter(
            StarterRepository(RoomStarterDataSource(androidContext()))
        )
    }

    factory {
        GetStarters(
            StarterRepository(RoomStarterDataSource(androidContext()))
        )
    }

    factory {
        Interactors(get(), get())
    }
}

// for testing

val testModule = module {

    factory {
        AddStarter(
            StarterRepository(InMemoryStartersDataSource.newInstance())
        )
    }

    factory {
        GetStarters(StarterRepository(InMemoryStartersDataSource.newInstance()))
    }

    factory {
        Interactors(get(), get())
    }
}