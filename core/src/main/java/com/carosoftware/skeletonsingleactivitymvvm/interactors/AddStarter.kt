package com.carosoftware.skeletonsingleactivitymvvm.interactors

import com.carosoftware.skeletonsingleactivitymvvm.data.StarterRepository
import com.carosoftware.skeletonsingleactivitymvvm.domain.StarterModel

class AddStarter(
    private val starterRepository: StarterRepository
) {

    // This enables you to simplify the function call on AddBookmark instance to addBookmark() instead of addBookmark.invoke().
    suspend operator fun invoke(starterModel: StarterModel) = starterRepository.add(starterModel)

}