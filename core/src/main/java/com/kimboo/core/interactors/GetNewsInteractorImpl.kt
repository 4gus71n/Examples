package com.kimboo.core.interactors

import com.kimboo.core.mappers.toNewsFeedMetadata
import com.kimboo.core.models.NewFeedMetadata
import com.kimboo.core.repositories.NewsRepository
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables

class GetNewsInteractorImpl(
    private val newsRepository: NewsRepository
) : GetNewsInteractor {
    override fun execute(callback: GetNewsInteractor.Callback, page: Int) {
        // If we are fetching the first page either because we did a PTR or because this is the
        // first time opening this screen we need to fetch both
        if (page < 1) {
            fetchRecentlyViewedNewsAndPagedNews(page).subscribe(
                {
                    val pagedNews = it.first
                    val recentlyViewedNews = it.second

                    callback.onNewsSuccessfullyFetched(
                        news = pagedNews.list,
                        currentPage = pagedNews.current,
                        totalPages = pagedNews.total
                    )

                    callback.onRecentlyViewedNewsFetched(
                        news = recentlyViewedNews.list
                    )
                },
                {
                    callback.onErrorFetchingNews()
                }
            )
        } else {
            fetchPagedNews(page).subscribe(
                {
                    callback.onNewsSuccessfullyFetched(
                        news = it.list,
                        currentPage = it.current,
                        totalPages = it.total
                    )
                },
                {
                    callback.onErrorFetchingNews()
                }
            )
        }
    }

    /**
     * Fetches both, the recently viewed news from the /news/recent and the paginated news from the
     * /news?page={page}
     */
    private fun fetchRecentlyViewedNewsAndPagedNews(page: Int): Observable<Pair<NewFeedMetadata, NewFeedMetadata>> {
        return Observables.zip(fetchPagedNews(page), fetchRecentlyViewedNews()) {
                pagedNews: NewFeedMetadata, recentlyViewedNews: NewFeedMetadata ->
                Pair(pagedNews, recentlyViewedNews)
        }
    }

    /**
     * Fetches the recently viewed news from the /news/recent endpoint.
     */
    private fun fetchRecentlyViewedNews(): Observable<NewFeedMetadata> {
        return newsRepository.fetchRecentlyViewedNews()
            .map {
                it.response.toNewsFeedMetadata()
            }
    }

    /**
     * Fetches the paginated news through the /news?page={page}
     */
    private fun fetchPagedNews(page: Int): Observable<NewFeedMetadata> {
        return newsRepository.fetchNews(page)
            .map {
                it.response.toNewsFeedMetadata()
            }
    }
}
