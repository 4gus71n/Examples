package com.kimboo.core.retrofit.api

import com.kimboo.core.retrofit.responses.ApiBusinessSkilssResponse
import com.kimboo.core.retrofit.responses.ApiNewFeedListResponse
import com.kimboo.core.retrofit.responses.ApiProfileResponse
import com.kimboo.core.retrofit.responses.ApiRecipeResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit's interface API.
 */
interface ExampleApi {
    /**
     * Fetches a paginated response containing the news.
     */
    @GET("news")
    fun fetchNews(
        @Query("page") page: Int
    ): Single<Response<ApiNewFeedListResponse>>

    /**
     * Fetches the most recently viewed news.
     */
    @GET("news/recent")
    fun fetchRecentlyViewedNews(): Single<Response<ApiNewFeedListResponse>>

    @GET("profile")
    fun fetchProfileInfo(   ): Single<Response<ApiProfileResponse>>

    @GET("business")
    fun fetchBusinessSkills(): Single<Response<ApiBusinessSkilssResponse>>

    @GET("recipes")
    fun fetchAllRecipes(): Single<Response<List<ApiRecipeResponse>>>
}