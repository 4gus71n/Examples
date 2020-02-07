package com.kimboo.core.retrofit.api

import com.kimboo.core.retrofit.responses.imgur.ApiCommentsResponse
import com.kimboo.core.retrofit.responses.imgur.ApiImgurGalleryResponse
import com.kimboo.core.retrofit.responses.imgur.ApiImgurTagResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurApi {
    @GET("gallery/{id}/comments/{sort}")
    fun fetchComments(
        @Path("id") id: String,
        @Path("sort") sort: String // best, new
    ) : Single<Response<ApiCommentsResponse>>

    @GET("gallery/search/{sort}/{window}/{page}")
    fun searchGallery(
        @Path("sort") sort: String = "top", // viral, top
        @Path("window") window: String = "all", // if sort == top { day, week, month }
        @Path("page") page: Int = 0,
        @Query("q") query: String = ""
    ) : Single<Response<ApiImgurGalleryResponse>>

    @GET("gallery/t/{tag}/{sort}/{page}")
    fun searchTags(
        @Path("sort") sort: String = "top", // viral, top
        @Path("page") page: Int = 0,
        @Path("tag") tag: String = "",
        @Query("perPage") perPage: Int = 20
    ) : Single<Response<ApiImgurTagResponse>>
}