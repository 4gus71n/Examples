package com.kimboo.core.retrofit.api

import com.kimboo.core.retrofit.responses.imgur.ApiCommentsResponse
import com.kimboo.core.retrofit.responses.imgur.ApiImgurGalleryResponse
import com.kimboo.core.retrofit.responses.imgur.ApiImgurTagResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit's interface API that we use to communicate with the Imgur API.
 */
interface ImgurApi {

    /**
     * Fetches the comments associated with one particular post.
     * @param id The id of the post.
     * @param sort "best" if we want to fetch the comments sorted by the most up voted. "new" if
     * we want to fetch the comments sorted by date.
     */
    @GET("gallery/{id}/comments/{sort}")
    fun fetchComments(
        @Path("id") id: String,
        @Path("sort") sort: String // best, new
    ) : Single<Response<ApiCommentsResponse>>

    /**
     * Fetches the posts using the specified search criteria.
     * @param sort "top" If we want to fetch the latest posts. "viral" If we want to fetch the
     * most trendy posts.
     * @param window If sort equals "viral" this parameter gets ignored. If sort equals "top" we
     * can specify if we want to fetch the most important posts from this "month", "day", or "week"
     * @param query The search query.
     * @param page The page number, starts from 1.
     */
    @GET("gallery/search/{sort}/{window}/{page}")
    fun searchGallery(
        @Path("sort") sort: String = "top", // viral, top
        @Path("window") window: String = "all", // if sort == top { day, week, month }
        @Path("page") page: Int = 1,
        @Query("q") query: String = ""
    ) : Single<Response<ApiImgurGalleryResponse>>

    /**
     * Fetches the posts using a tag.
     * @param sort "top" If we want to fetch the latest posts. "viral" If we want to fetch the
     * most trendy posts.
     * @param perPage Max amount of results per page.
     * @param tag The tag query.
     * @param page The page number, starts from 1.
     */
    @GET("gallery/t/{tag}/{sort}/{page}")
    fun searchTags(
        @Path("sort") sort: String = "top", // viral, top
        @Path("page") page: Int = 0,
        @Path("tag") tag: String = "",
        @Query("perPage") perPage: Int = 20
    ) : Single<Response<ApiImgurTagResponse>>
}