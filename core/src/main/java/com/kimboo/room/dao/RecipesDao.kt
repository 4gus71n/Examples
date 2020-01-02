package com.kimboo.room.dao

import androidx.room.*
import com.kimboo.room.dto.DbRecipesDto
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface RecipesDao {
    @Query("SELECT * FROM db_recipes_dto")
    fun getAllSquareRepositories(): Flowable<List<DbRecipesDto>>

    @Query("SELECT * FROM db_recipes_dto where id = :id")
    fun getSquareRepositoryById(id: String): Flowable<DbRecipesDto>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun storeSquareRepositories(
        dbSquareRepositoryDtos: List<DbRecipesDto>
    ): Single<LongArray>

    @Update
    fun updateSquareRepository(dbSquareRepositoryDto: DbRecipesDto): Single<Int>
}