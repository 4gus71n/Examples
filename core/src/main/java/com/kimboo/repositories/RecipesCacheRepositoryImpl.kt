package com.kimboo.repositories

import com.kimboo.room.dao.RecipesDao
import com.kimboo.room.dto.DbRecipeDto
import com.kimboo.utils.DataResponse
import com.kimboo.utils.toCachedDataResponse
import io.reactivex.Observable
import io.reactivex.Scheduler

class RecipesCacheRepositoryImpl(
    private val recepiesDao: RecipesDao,
    private val uiScheduler: Scheduler,
    private val backgroundScheduler: Scheduler
) : RecipesCacheRepository {
    override fun storeRecipes(recipes: List<DbRecipeDto>): Observable<DataResponse<Boolean>> {
        return recepiesDao.storeRecipes(recipes)
            // The observable replies with true if anything changed on the DB
            .map { it.isNotEmpty() }
            .toCachedDataResponse()
            .observeOn(uiScheduler)
            .subscribeOn(backgroundScheduler)
    }

    override fun updateRecipe(recipe: DbRecipeDto): Observable<DataResponse<Boolean>> {
        return recepiesDao.updateRecipes(recipe)
            // The observable replies with true if anything changed on the DB
            .map { it > 0 }
            .toCachedDataResponse()
            .observeOn(uiScheduler)
            .subscribeOn(backgroundScheduler)
    }

    override fun getAllRecipes(): Observable<DataResponse<List<DbRecipeDto>>> {
        return recepiesDao.getAllRecipes()
            .toCachedDataResponse()
            .observeOn(uiScheduler)
            .subscribeOn(backgroundScheduler)
    }

    override fun getRecipeById(
        recipeId: Int
    ): Observable<DataResponse<DbRecipeDto>> {
        return recepiesDao.getRecipeById(recipeId)
        .toCachedDataResponse()
        .observeOn(uiScheduler)
        .subscribeOn(backgroundScheduler)
    }
}