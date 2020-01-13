package com.kimboo.interactors

interface BookmarkRecipeInteractor {
    interface Callback {
        fun onSuccessfullyBookmarkedRecipe(
            isBookmarked: Boolean
        )
        fun onErrorFetchingRecipes()
    }

    fun execute(recipeId: Int, callback: Callback)
}