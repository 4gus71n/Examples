package com.kimboo.core.repositories

import com.kimboo.core.retrofit.api.ExampleApi
import com.kimboo.core.retrofit.responses.ApiNewFeedListResponse
import com.kimboo.core.utils.DataResponse
import com.kimboo.core.utils.toDataResponse
import io.reactivex.Observable
import io.reactivex.Scheduler

class NewsNetworkRepository (
    private val retrofitApi: ExampleApi,
    private val uiScheduler: Scheduler,
    private val backgroundScheduler: Scheduler
) : NewsRepository {
    override fun fetchRecentlyViewedNews(): Observable<DataResponse<ApiNewFeedListResponse>> {
        return retrofitApi.fetchRecentlyViewedNews()
            .toDataResponse()
            .observeOn(uiScheduler)
            .subscribeOn(backgroundScheduler)
    }

    override fun fetchNews(page: Int): Observable<DataResponse<ApiNewFeedListResponse>> {
        return retrofitApi.fetchNews(page)
            .toDataResponse()
            .observeOn(uiScheduler)
            .subscribeOn(backgroundScheduler)
    }
}
