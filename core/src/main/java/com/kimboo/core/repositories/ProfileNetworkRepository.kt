package com.kimboo.core.repositories

import com.kimboo.core.retrofit.api.ExampleApi
import com.kimboo.core.retrofit.responses.ApiBusinessSkilssResponse
import com.kimboo.core.retrofit.responses.ApiProfileResponse
import com.kimboo.core.utils.DataResponse
import com.kimboo.core.utils.toDataResponse
import io.reactivex.Observable
import io.reactivex.Scheduler

class ProfileNetworkRepository (
    private val retrofitApi: ExampleApi,
    private val uiScheduler: Scheduler,
    private val backgroundScheduler: Scheduler
) : ProfileRepository {
    override fun fetchProfileInformation(): Observable<DataResponse<ApiProfileResponse>> {
        return retrofitApi.fetchProfileInfo()
            .toDataResponse()
            .observeOn(uiScheduler)
            .subscribeOn(backgroundScheduler)
    }

    override fun fetchBusinessSkils(): Observable<DataResponse<ApiBusinessSkilssResponse>> {
        return retrofitApi.fetchBusinessSkills()
            .toDataResponse()
            .observeOn(uiScheduler)
            .subscribeOn(backgroundScheduler)
    }
}
