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
 * Retrofit's interface that we use to communicate with a mock API hosted on apiary.io
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

    /**
     * Fetches the user profile information
     */
    @GET("profile")
    fun fetchProfileInfo(   ): Single<Response<ApiProfileResponse>>

    /**
     * Fetches the user business skills
     */
    @GET("business")
    fun fetchBusinessSkills(): Single<Response<ApiBusinessSkilssResponse>>

    /**
     * Fetches a list of cooking recipes
     */
    @GET("recipes")
    fun fetchAllRecipes(): Single<Response<List<ApiRecipeResponse>>>
}