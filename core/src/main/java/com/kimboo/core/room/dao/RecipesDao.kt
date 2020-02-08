package com.kimboo.core.room.dao

import androidx.room.*
import com.kimboo.core.room.dto.DbRecipeDto
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class RecipesDao {
    @Query("SELECT * FROM db_recipe_dto")
    abstract fun getAllRecipes(): Flowable<List<DbRecipeDto>>

    @Query("SELECT * FROM db_recipe_dto where id = :id")
    abstract fun getRecipeById(id: Int): Flowable<DbRecipeDto>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun storeRecipes(
        dbRecipeDtos: List<DbRecipeDto>
    ): Single<LongArray>

    @Update
    abstract fun updateRecipes(dbRecipeDto: DbRecipeDto): Single<Int>
}

