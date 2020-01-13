package com.kimboo.interactors

import com.kimboo.models.Recipe

interface GetRecipeByIdInteractor {
    interface Callback {
        fun onSuccessfullyFetchedRecipe(
            recipes: Recipe
        )
        fun onErrorFetchingRecipe()
    }

    fun connect(recipeId: Int, callback: Callback)
}