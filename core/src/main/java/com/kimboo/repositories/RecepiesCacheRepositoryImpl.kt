package com.kimboo.repositories

import com.kimboo.room.dao.RecipesDao
import com.kimboo.room.dto.DbRecepieDto
import com.kimboo.utils.DataResponse
import com.kimboo.utils.toCachedDataResponse
import io.reactivex.Observable
import io.reactivex.Scheduler

class RecepiesCacheRepositoryImpl(
    private val recepiesDao: RecipesDao,
    private val uiScheduler: Scheduler,
    private val backgroundScheduler: Scheduler
) : RecepiesCacheRepository {
    override fun storeRecepies(recepies: List<DbRecepieDto>): Observable<DataResponse<Boolean>> {
        return recepiesDao.storeRecipes(recepies)
            // The observable replies with true if anything changed on the DB
            .map { it.isNotEmpty() }
            .toCachedDataResponse()
            .observeOn(uiScheduler)
            .subscribeOn(backgroundScheduler)
    }

    override fun updateRecepie(recepie: DbRecepieDto): Observable<DataResponse<Boolean>> {
        return recepiesDao.updateRecipes(recepie)
            // The observable replies with true if anything changed on the DB
            .map { it > 0 }
            .toCachedDataResponse()
            .observeOn(uiScheduler)
            .subscribeOn(backgroundScheduler)
    }

    override fun getAllRecepies(): Observable<DataResponse<List<DbRecepieDto>>> {
        return recepiesDao.getAllRecipes()
            .toCachedDataResponse()
            .observeOn(uiScheduler)
            .subscribeOn(backgroundScheduler)
    }
}