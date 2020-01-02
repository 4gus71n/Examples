package com.kimboo.repositories

import com.kimboo.room.dto.DbRecepieDto
import com.kimboo.utils.DataResponse
import io.reactivex.Observable

interface RecepiesCacheRepository{
    fun storeRecepies(
        recepies: List<DbRecepieDto>
    ): Observable<DataResponse<Boolean>>

    fun updateRecepie(
        recepie: DbRecepieDto
    ): Observable<DataResponse<Boolean>>

    fun getAllRecepies(): Observable<DataResponse<List<DbRecepieDto>>>
}