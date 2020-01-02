package com.kimboo.repositories

import com.kimboo.room.dto.DbRecipeDto
import com.kimboo.utils.DataResponse
import io.reactivex.Observable

interface RecipesCacheRepository{
    fun storeRecipes(
        recipes: List<DbRecipeDto>
    ): Observable<DataResponse<Boolean>>

    fun updateRecipe(
        recipe: DbRecipeDto
    ): Observable<DataResponse<Boolean>>

    fun getAllRecipes(): Observable<DataResponse<List<DbRecipeDto>>>
}