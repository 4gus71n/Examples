package com.kimboo.room.dao

import androidx.room.*
import com.kimboo.room.dto.DbRecepieDto
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface RecipesDao {
    @Query("SELECT * FROM db_recipes_dto")
    fun getAllSquareRepositories(): Flowable<List<DbRecepieDto>>

    @Query("SELECT * FROM db_recipes_dto where id = :id")
    fun getSquareRepositoryById(id: String): Flowable<DbRecepieDto>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun storeSquareRepositories(
        dbSquareRepositoryDtos: List<DbRecepieDto>
    ): Single<LongArray>

    @Update
    fun updateSquareRepository(dbSquareRepositoryDto: DbRecepieDto): Single<Int>
}