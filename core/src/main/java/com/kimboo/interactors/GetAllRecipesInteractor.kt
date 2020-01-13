package com.kimboo.interactors

import com.kimboo.models.Recipe

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