package com.kimboo.repositories

import com.kimboo.retrofit.api.ExampleApi
import com.kimboo.retrofit.responses.ApiBusinessSkilssResponse
import com.kimboo.retrofit.responses.ApiNewFeedListResponse
import com.kimboo.retrofit.responses.ApiProfileResponse
import com.kimboo.utils.DataResponse
import com.kimboo.utils.toDataResponse
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
