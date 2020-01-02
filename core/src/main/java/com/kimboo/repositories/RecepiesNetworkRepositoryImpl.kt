package com.kimboo.repositories

import com.kimboo.retrofit.api.ExampleApi
import com.kimboo.retrofit.responses.ApiRecipeResponse
import com.kimboo.utils.DataResponse
import com.kimboo.utils.toDataResponse
import io.reactivex.Observable
import io.reactivex.Scheduler

class RecepiesNetworkRepositoryImpl(
    private val retrofitApi: ExampleApi,
    private val uiScheduler: Scheduler,
    private val backgroundScheduler: Scheduler
) : RecepiesNetworkRepository {
    override fun fetchRecepies(): Observable<DataResponse<List<ApiRecipeResponse>>> {
        return retrofitApi.fetchAllRecipes()
            .toDataResponse()
            .observeOn(uiScheduler)
            .subscribeOn(backgroundScheduler)
    }
}