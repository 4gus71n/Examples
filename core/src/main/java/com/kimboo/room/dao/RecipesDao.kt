package com.kimboo.room.dao

import androidx.room.*
import com.kimboo.room.dto.DbRecipeDto
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface RecipesDao {
    @Query("SELECT * FROM db_recipe_dto")
    fun getAllRecipes(): Flowable<List<DbRecipeDto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun storeRecipes(
        dbRecipeDtos: List<DbRecipeDto>
    ): Single<LongArray>

    @Update
    fun updateRecipes(dbRecipeDto: DbRecipeDto): Single<Int>
}