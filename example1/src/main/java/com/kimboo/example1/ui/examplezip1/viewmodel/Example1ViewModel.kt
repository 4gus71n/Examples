package com.kimboo.example1.ui.examplezip1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimboo.interactors.GetNewsInteractor
import com.kimboo.models.NewFeed
import javax.inject.Inject

class Example1ViewModel @Inject constructor(
    private val getNewsInteractor: GetNewsInteractor
) : ViewModel(), GetNewsInteractor.Callback {

    sealed class State {
        data class NewsFetched(
            val news: List<NewFeed>
        ) : State()
        data class RecentlyViewedNewsFetched(
            val news: List<NewFeed>
        ) : State()
        object Error : State()
    }

    data class Pagination(
        val currentPage: Int,
        val totalPages: Int
    )

    val pagination = MutableLiveData<Pagination>()
    val isLoading = MutableLiveData<Boolean>()
    val state = MutableLiveData<State>()

    // region GetNewsInteractor.Callback implementation
    override fun onNewsSuccessfullyFetched(news: List<NewFeed>, currentPage: Int, totalPages: Int) {
        isLoading.value = false

        state.value =
            State.NewsFetched(
                news = news
            )

        pagination.value =
            Pagination(
                currentPage = currentPage,
                totalPages = totalPages
            )
    }

    override fun onRecentlyViewedNewsFetched(news: List<NewFeed>) {
        isLoading.value = false

        state.value =
            State.RecentlyViewedNewsFetched(
                news = news
            )
    }

    override fun onErrorFetchingNews() {
        isLoading.value = false

        state.value = State.Error
    }
    // endregion

    fun fetchNews(page: Int) {
        isLoading.value = true
        getNewsInteractor.execute(this, page)
    }
}