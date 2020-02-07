package com.kimboo.core.repositories.imgur

import com.kimboo.core.retrofit.api.ImgurApi
import com.kimboo.core.retrofit.responses.imgur.ApiImgurGalleryResponse
import com.kimboo.core.retrofit.responses.imgur.ApiImgurTagResponse
import com.kimboo.core.utils.DataResponse
import com.kimboo.core.utils.toDataResponse
import io.reactivex.Observable
import io.reactivex.Scheduler

class ApiImgurGalleryRepositoryImpl(
    private val imgurApi: ImgurApi,
    private val uiScheduler: Scheduler,
    private val backgroundScheduler: Scheduler
) : ApiImgurGalleryRepository {

    override fun fetchGalleryPostsByTagSearch(
        page: Int,
        sort: String
    ): Observable<DataResponse<ApiImgurTagResponse>> {
        return imgurApi.searchTags(
            page = page,
            sort = sort,
            tag = "cats"
        )
        .toDataResponse()
        .observeOn(uiScheduler)
        .subscribeOn(backgroundScheduler)
    }

    override fun fetchGalleryPostsByQuerySearch(
        query: String,
        page: Int,
        sort: String,
        window: String
    ): Observable<DataResponse<ApiImgurGalleryResponse>> {
        return imgurApi.searchGallery(
            page = page,
            query = query,
            sort = sort,
            window = window
        )
        .toDataResponse()
        .observeOn(uiScheduler)
        .subscribeOn(backgroundScheduler)
    }
}