package com.kimboo.core.interactors

import com.kimboo.core.models.imgur.ImgurGallery

interface GetImgurPostsInteractor {
    interface Callback  {
        fun onSucessfullyFetchedImgurPosts(
            query: String,
            list: List<ImgurGallery>
        )
        fun onErrorFecthingImgurPosts()
        fun onNoInternetConnection()
    }
    fun execute(
        callback: Callback,
        query: String,
        page: Int,
        sort: String, // viral, top
        window: String // if sort == top { day, week, month }
    )
    fun dispose()
}