package com.kimboo.interactors

import com.kimboo.mappers.fromApiListToModelList
import com.kimboo.mappers.fromDbListToModelList
import com.kimboo.mappers.fromModelListToDbList
import com.kimboo.repositories.RecipesCacheRepository
import com.kimboo.repositories.RecipesNetworkRepository

class GetAllRecipesInteractorImpl(
    private val recipesNetworkRepository: RecipesNetworkRepository,
    private val recipesCacheRepository: RecipesCacheRepository
) : GetAllRecipesInteractor {

    override fun connect(callback: GetAllRecipesInteractor.Callback) {
        recipesCacheRepository.getAllRecipes().subscribe(
            {
                val recipes = it.response.fromDbListToModelList()
                callback.onSuccessfullyFetchedAllRecipes(recipes)
            },
            {
                it.printStackTrace()
            }
        )

        recipesNetworkRepository.fetchRecepies()
            .flatMap {
                val fetchedRecipes = it.response.fromApiListToModelList()
                val storedRecipes = fetchedRecipes.fromModelListToDbList()
                recipesCacheRepository.storeRecipes(storedRecipes)
            }
            .subscribe(
                {
                    it.toString()
                },
                {
                    it.printStackTrace()
                }
            )
    }

    override fun disconnect() {

    }
}