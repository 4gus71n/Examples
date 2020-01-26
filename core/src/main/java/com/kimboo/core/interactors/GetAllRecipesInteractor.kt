package com.kimboo.core.interactors

import com.kimboo.core.models.Recipe

interface GetAllRecipesInteractor {
    interface Callback {
        fun onSuccessfullyFetchedAllRecipes(
            recipes: List<Recipe>
        )
        fun onErrorFetchingRecipes()
    }

    fun connect(callback: Callback)
    fun disconnect()
}