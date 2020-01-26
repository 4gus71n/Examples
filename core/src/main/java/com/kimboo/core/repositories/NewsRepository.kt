package com.kimboo.core.repositories

import com.kimboo.core.retrofit.responses.ApiNewFeedListResponse
import com.kimboo.core.utils.DataResponse
import io.reactivex.Observable

interface NewsRepository {
    fun fetchRecentlyViewedNews() : Observable<DataResponse<ApiNewFeedListResponse>>
    fun fetchNews(page: Int) : Observable<DataResponse<ApiNewFeedListResponse>>
}