package com.kimboo.core.repositories

import com.kimboo.core.room.dto.DbRecipeDto
import com.kimboo.core.utils.DataResponse
import io.reactivex.Observable

interface RecipesCacheRepository{
    fun storeRecipes(
        recipes: List<DbRecipeDto>
    ): Observable<DataResponse<Boolean>>

    fun updateRecipe(
        recipe: DbRecipeDto
    ): Observable<DataResponse<Boolean>>

    fun getAllRecipes(): Observable<DataResponse<List<DbRecipeDto>>>

    fun getRecipeById(
        recipeId: Int
    ): Observable<DataResponse<DbRecipeDto>>
}