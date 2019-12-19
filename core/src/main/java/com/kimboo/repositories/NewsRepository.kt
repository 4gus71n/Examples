package com.kimboo.repositories

import com.kimboo.retrofit.responses.ApiNewFeedListResponse
import com.kimboo.retrofit.responses.ApiNewFeedResponse
import com.kimboo.utils.DataResponse
import io.reactivex.Observable

interface NewsRepository {
    fun fetchRecentlyViewedNews() : Observable<DataResponse<ApiNewFeedListResponse>>
    fun fetchNews(page: Int) : Observable<DataResponse<ApiNewFeedListResponse>>
}