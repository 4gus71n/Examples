package com.kimboo.core.repositories.imgur

import com.kimboo.core.retrofit.responses.imgur.ApiImgurGalleryResponse
import com.kimboo.core.retrofit.responses.imgur.ApiImgurTagResponse
import com.kimboo.core.utils.DataResponse
import io.reactivex.Observable
import io.reactivex.Single

interface ApiImgurGalleryRepository {
    fun fetchGalleryPostsByQuerySearch(
        query: String,
        page: Int,
        sort: String,
        window: String
    ): Observable<DataResponse<ApiImgurGalleryResponse>>

    fun fetchGalleryPostsByTagSearch(
        page: Int,
        sort: String
    ): Observable<DataResponse<ApiImgurTagResponse>>
}