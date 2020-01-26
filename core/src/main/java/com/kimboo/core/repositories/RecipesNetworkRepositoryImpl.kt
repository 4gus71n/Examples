package com.kimboo.core.repositories

import com.kimboo.core.retrofit.api.ExampleApi
import com.kimboo.core.retrofit.responses.ApiRecipeResponse
import com.kimboo.core.utils.DataResponse
import com.kimboo.core.utils.toDataResponse
import io.reactivex.Observable
import io.reactivex.Scheduler

class RecipesNetworkRepositoryImpl(
    private val retrofitApi: ExampleApi,
    private val uiScheduler: Scheduler,
    private val backgroundScheduler: Scheduler
) : RecipesNetworkRepository {
    override fun fetchRecepies(): Observable<DataResponse<List<ApiRecipeResponse>>> {
        return retrofitApi.fetchAllRecipes()
            .toDataResponse()
            .observeOn(uiScheduler)
            .subscribeOn(backgroundScheduler)
    }
}