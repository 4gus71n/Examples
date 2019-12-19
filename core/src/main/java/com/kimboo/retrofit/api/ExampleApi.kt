package com.kimboo.retrofit.api

import com.kimboo.retrofit.responses.ApiNewFeedListResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit's interface API.
 */
interface ExampleApi {
    @GET("news")
    fun fetchNews(
        @Query("page") page: Int
    ): Single<Response<ApiNewFeedListResponse>>

    @GET("news/recent")
    fun fetchRecentlyViewedNews(): Single<Response<ApiNewFeedListResponse>>
}