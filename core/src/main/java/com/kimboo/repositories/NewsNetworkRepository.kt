package com.kimboo.repositories

import com.kimboo.retrofit.api.ExampleApi
import com.kimboo.retrofit.responses.ApiNewFeedListResponse
import com.kimboo.utils.DataResponse
import com.kimboo.utils.toDataResponse
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
