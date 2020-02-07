package com.kimboo.example3.ui.search.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimboo.core.interactors.GetImgurPostsInteractor
import com.kimboo.core.models.imgur.ImgurGallery
import javax.inject.Inject

class ExampleImgurViewModel @Inject constructor(
    private val getImgurPostsInteractor: GetImgurPostsInteractor
) : ViewModel(), GetImgurPostsInteractor.Callback {

    // region Sealed classes declaration
    sealed class State {
        data class IsLoading(
            val isLoading: Boolean
        ) : State()
        data class Success(
            val query: String,
            val list: List<ImgurGallery>
        ) : State()
        object NoInternetError : State()
        object UnknownError : State()
    }

    sealed class Message {
        object NoInternetError : Message()
        object UnknownError : Message()
    }
    // endregion

    // region Enum classes declaration
    sealed class SearchParams {
        enum class Sort(val value: String) {
            TOP("top"),
            VIRAL("viral")
        }
        enum class Window(val value: String) {
            DAY("day"),
            WEEK("week"),
            MONTH("month")
        }
        data class Query(
            val query: String,
            val window: Window,
            val sort: Sort,
            val page: Int
        )
    }
    // endregion


    // region Variables declaration
    private val _imgurGalleryPosts = mutableListOf<ImgurGallery>()

    val state = MutableLiveData<State>()
    val message = MutableLiveData<Message>()

    private var _searchParams = SearchParams.Query(
        query = "",
        window = SearchParams.Window.MONTH,
        sort = SearchParams.Sort.TOP,
        page = 1
    )

    val sort = MutableLiveData<SearchParams.Sort>().apply {
        SearchParams.Sort.TOP // Default sort
    }
    val window = MutableLiveData<SearchParams.Window>().apply {
        SearchParams.Window.MONTH // Default window
    }
    val query = MutableLiveData<String>().apply {
        "" // Default query
    }
    private val page = MutableLiveData<Int>().apply {
        1 // Default page
    }

    // LiveData property that reflects the state of what we are currently searching.
    val searchParams = MediatorLiveData<SearchParams.Query>().apply {
        addSource(sort) { newSort ->
            // Remove all the current posts since the query has changed
            _imgurGalleryPosts.clear()
            value = _searchParams.copy(
                sort = newSort
            )
        }
        addSource(window) { newWindow ->
            // Remove all the current posts since the query has changed
            _imgurGalleryPosts.clear()
            value = _searchParams.copy(
                window = newWindow
            )
        }
        addSource(query) { newQuery ->
            // Remove all the current posts since the query has changed
            _imgurGalleryPosts.clear()
            value = _searchParams.copy(
                query = newQuery
            )
        }
        addSource(page) { newPage ->
            value = _searchParams.copy(
                page = newPage
            )
        }
    }
    // endregion

    // region GetImgurPostsInteractor.Callback implementation
    override fun onSucessfullyFetchedImgurPosts(
        query: String,
        list: List<ImgurGallery>
    ) {
        state.value = State.IsLoading(false)

        _imgurGalleryPosts.addAll(list)

        state.value = State.Success(
            query = _searchParams.query,
            list = _imgurGalleryPosts
        )
    }

    override fun onErrorFecthingImgurPosts() {
        state.value = State.IsLoading(false)
        if (_imgurGalleryPosts.isEmpty()) {
            state.value = State.UnknownError
        } else {
            message.value = Message.UnknownError
        }
    }

    override fun onNoInternetConnection() {
        state.value = State.IsLoading(false)
        if (_imgurGalleryPosts.isEmpty()) {
            state.value = State.NoInternetError
        } else {
            message.value = Message.NoInternetError
        }
    }

    // endregion

    fun searchPosts(
        searchParams: SearchParams.Query
    ) {
        _searchParams = searchParams

        state.value = State.IsLoading(true)

        getImgurPostsInteractor.execute(
            callback = this,
            query = _searchParams.query,
            sort = _searchParams.sort.value,
            window = _searchParams.window.value,
            page = _searchParams.page
        )
    }

    fun setWindow(windowValue: SearchParams.Window) {
        window.value = windowValue
    }

    fun setSort(sortValue: SearchParams.Sort) {
        sort.value = sortValue
    }

    fun setQuery(queryValue: String) {
        query.value = queryValue
    }

    fun setPage(pageValue: Int) {
        page.value = pageValue
    }

    override fun onCleared() {
        super.onCleared()
        getImgurPostsInteractor.dispose()
    }
}