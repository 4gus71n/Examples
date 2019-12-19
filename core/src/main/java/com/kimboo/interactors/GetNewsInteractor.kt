package com.kimboo.interactors

import com.kimboo.models.NewFeed

interface GetNewsInteractor {
    interface Callback {
        fun onNewsSuccessfullyFetched(
            news: List<NewFeed>,
            currentPage: Int,
            totalPages: Int
        )
        fun onRecentlyViewedNewsFetched(
            news: List<NewFeed>
        )
        fun onErrorFetchingNews()
    }
    fun execute(callback: Callback, page: Int)
}