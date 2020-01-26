package com.kimboo.core.interactors

import com.kimboo.core.models.Recipe

interface GetRecipeByIdInteractor {
    interface Callback {
        fun onSuccessfullyFetchedRecipe(
            recipes: Recipe
        )
        fun onErrorFetchingRecipe()
    }

    fun connect(recipeId: Int, callback: Callback)
}