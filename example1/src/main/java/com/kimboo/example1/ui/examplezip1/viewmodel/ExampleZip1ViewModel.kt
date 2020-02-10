package com.kimboo.example1.ui.examplezip1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimboo.core.interactors.GetNewsInteractor
import com.kimboo.core.models.NewFeed
import javax.inject.Inject

class ExampleZip1ViewModel @Inject constructor(
    private val getNewsInteractor: GetNewsInteractor
) : ViewModel(), GetNewsInteractor.Callback {

    // region Sealed classes declaration
    sealed class State {
        data class NewsFetched(
            val news: List<NewFeed>
        ) : State()
        data class RecentlyViewedNewsFetched(
            val news: List<NewFeed>
        ) : State()
        data class IsLoading(
            val loading: Boolean
        ) : State()
        object UnknownError : State()
        object NoInternetConnection : State()
    }

    sealed class Message {
        object UnknownError : Message()
        object NoInternetConnection : Message()
    }

    data class Pagination(
        val currentPage: Int,
        val totalPages: Int
    )
    // endregion

    // region Variables declaration
    val pagination = MutableLiveData<Pagination>()
    val state = MutableLiveData<State>()
    val message = MutableLiveData<Message>()

    private val _news = mutableListOf<NewFeed>()
    private val _recentNews = mutableListOf<NewFeed>()
    // endregion

    // region GetNewsInteractor.Callback implementation
    override fun onNewsSuccessfullyFetched(news: List<NewFeed>, currentPage: Int, totalPages: Int) {
        state.value = State.IsLoading(false)

        if (currentPage == 0) {
            _news.clear()
        }
        _news.addAll(news)

        state.value = State.NewsFetched(_news)

        pagination.value = Pagination(
            currentPage = currentPage,
            totalPages = totalPages
        )
    }

    override fun onRecentlyViewedNewsFetched(news: List<NewFeed>) {
        _recentNews.clear()
        _recentNews.addAll(news)

        state.value = State.IsLoading(false)
        state.value = State.RecentlyViewedNewsFetched(_recentNews)
    }

    override fun onErrorFetchingNews() {
        state.value = State.IsLoading(false)
        if (_recentNews.isEmpty() && _news.isEmpty()) {
            state.value = State.UnknownError
        } else {
            message.value = Message.UnknownError
        }
    }

    override fun onNoInternetConnection() {
        state.value = State.IsLoading(false)
        if (_recentNews.isEmpty() && _news.isEmpty()) {
            state.value = State.NoInternetConnection
        } else {
            message.value = Message.NoInternetConnection
        }
    }

    // endregion

    fun fetchNews(page: Int) {
        state.value = State.IsLoading(true)
        getNewsInteractor.execute(this, page)
    }
}