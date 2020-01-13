package com.kimboo.interactors

import com.kimboo.repositories.RecipesCacheRepository

/**
 * This Interactor marks a recipe as "bookmarked" on the DB.
 */
class BookmarkRecipeInteractorImpl(
    private val recipesCacheRepository: RecipesCacheRepository
) : BookmarkRecipeInteractor {
    override fun execute(recipeId: Int, callback: BookmarkRecipeInteractor.Callback) {
        recipesCacheRepository.getRecipeById(recipeId).take(1).flatMap {
            // Get the DB recipe
            val originalRecipe = it.response
            // Toogle the isBookmarked value
            val updatedRecipe = originalRecipe.copy(
                isBookmarked = !originalRecipe.isBookmarked
            )
            // Update the DB
            recipesCacheRepository.updateRecipe(updatedRecipe)
        }
        .subscribe(
            {
                callback.onSuccessfullyBookmarkedRecipe(it.response)
            },
            {
                callback.onErrorFetchingRecipes()
            }
        )
    }
}