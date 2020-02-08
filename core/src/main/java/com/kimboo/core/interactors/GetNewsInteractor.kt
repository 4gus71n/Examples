package com.kimboo.core.interactors

import com.kimboo.core.models.NewFeed

/**
 * Fetches the recently viewed news and the paginated news altogether.
 */
interface GetNewsInteractor {
    interface Callback {
        /**
         * Triggered when we successfully fetch the paginated news
         * @param news The list of news for this page.
         * @param currentPage The current page.
         * @param totalPages The total amount of pages available.
         */
        fun onNewsSuccessfullyFetched(
            news: List<NewFeed>,
            currentPage: Int,
            totalPages: Int
        )

        /**
         * Triggered when we successfully fetch the recently viewed news.
         * @param news The list of recently viewed news.
         */
        fun onRecentlyViewedNewsFetched(
            news: List<NewFeed>
        )
        /**
         * Triggered when something goes wrong either fetching the recently viewed news or
         * the paginated news.
         */
        fun onErrorFetchingNews()
    }

    /**
     * Execute function to trigger this Interactor.
     * @param callback A GetNewsInteractor.Callback usually implemented on the ViewModel.
     * @param page The current page that we are trying to fetch.
     */
    fun execute(callback: Callback, page: Int)
}


