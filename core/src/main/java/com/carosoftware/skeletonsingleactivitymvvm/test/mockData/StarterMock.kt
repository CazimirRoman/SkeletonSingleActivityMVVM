package com.carosoftware.skeletonsingleactivitymvvm.test.mockData

import com.carosoftware.skeletonsingleactivitymvvm.domain.StarterModel

class StarterMock {

    companion object {
        fun singleStarter(): StarterModel {
            return StarterModel(text = "textSingle", size = 0)
        }

        fun listOfStarters(type: StarterMockType): List<StarterModel> {

            val list = mutableListOf<StarterModel>()

            when(type) {
                StarterMockType.LOCAL -> {
                    for (item in 1..7) {
                        list.add(StarterModel(text = "text$item", size = item))
                    }
                }
                StarterMockType.REMOTE -> {
                    for (item in 1..20) {
                        list.add(StarterModel(text = "text$item", size = item))
                    }
                }
            }

            return list
        }
    }
}

enum class StarterMockType {
    LOCAL, REMOTE
}