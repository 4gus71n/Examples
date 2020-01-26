package com.kimboo.core.interactors

import com.kimboo.core.mappers.fromDbToModel
import com.kimboo.core.repositories.RecipesCacheRepository

class GetRecipeByIdInteractorImpl(
    private val recipesCacheRepository: RecipesCacheRepository
) : GetRecipeByIdInteractor {
    override fun connect(recipeId: Int, callback: GetRecipeByIdInteractor.Callback) {
        recipesCacheRepository.getRecipeById(recipeId)
            .map { it.response.fromDbToModel() }
            .subscribe(
                {
                    callback.onSuccessfullyFetchedRecipe(it)
                },
                {
                    callback.onErrorFetchingRecipe()
                }
            )
    }
}