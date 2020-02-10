package com.kimboo.example1.ui.examplezip1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.kimboo.base.utils.PaginatedScrollListener
import com.kimboo.example1.R
import com.kimboo.example1.di.component.DaggerExample1ViewInjector
import com.kimboo.example1.di.component.Example1ViewInjector
import com.kimboo.example1.ui.examplezip1.adapter.NewsAdapter
import com.kimboo.example1.ui.examplezip1.viewmodel.ExampleZip1ViewModel
import com.kimboo.core.utils.MyViewModelFactory
import com.kimboo.core.utils.getBaseSubComponent
import kotlinx.android.synthetic.main.activity_example_1.*
import javax.inject.Inject
import kotlin.math.abs

class ExampleZip1Activity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: MyViewModelFactory
    lateinit var viewModelZip: ExampleZip1ViewModel

    private val viewInjector: Example1ViewInjector
         get() = DaggerExample1ViewInjector.builder()
             .baseSubComponent(getBaseSubComponent())
             .build()

    private val newsAdapter = NewsAdapter(this)
    private val paginatedScrollListener = object : PaginatedScrollListener() {
        override fun onLoadNextPage(page: Int) {
            viewModelZip.fetchNews(page)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_1)

        setupRecyclerView()
        setupSwipeRefreshLayoutView()
        setupParallaxFadeOut()
        setupToolbar()

        viewInjector.inject(this)

        viewModelZip = ViewModelProviders.of(this, viewModelProvider)
            .get(ExampleZip1ViewModel::class.java)

        observeStateChanges()
        observeMessageChanges()
        obserPaginationChanges()

        fetchNews()
    }

    private fun setupToolbar() {
        setSupportActionBar(activityExample1Toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupParallaxFadeOut() {
        activityExample1AppbarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            activityExample1DescriptionContainer.alpha = 1 - abs(
                verticalOffset.toFloat() / appBarLayout.totalScrollRange
            )
        })
    }

    private fun setupSwipeRefreshLayoutView() {
        activityExample1SwipeRefreshLayout.setOnRefreshListener {
            fetchNews()
        }
    }

    private fun setupRecyclerView() {
        example1ActivityPagedNewsRecyclerView.addOnScrollListener(paginatedScrollListener)
        example1ActivityPagedNewsRecyclerView.adapter = newsAdapter
    }

    private fun obserPaginationChanges() {
        viewModelZip.pagination.observe(this, Observer {
            paginatedScrollListener.currentPage = it.currentPage
            paginatedScrollListener.isLastPage = it.currentPage == it.totalPages
        })
    }

    private fun observeMessageChanges() {
        viewModelZip.message.observe(this, Observer {
            when (it) {
                is ExampleZip1ViewModel.Message.NoInternetConnection -> {
                    onShowNoInternetConnectionSnackbar()
                }
                is ExampleZip1ViewModel.Message.UnknownError -> {
                    onShowUnknownErrorSnackbar()
                }
            }
        })
    }

    private fun onShowUnknownErrorSnackbar() {
        Snackbar.make(
            example1ActivityContainer,
            getString(R.string.error_unknown_error),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun onShowNoInternetConnectionSnackbar() {
        Snackbar.make(
            example1ActivityContainer,
            getString(R.string.error_no_internet_connection),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun observeStateChanges() {
        viewModelZip.state.observe(this, Observer {
            when (it) {
                is ExampleZip1ViewModel.State.IsLoading -> {
                    onChangeLoadingState(it.loading)
                }
                is ExampleZip1ViewModel.State.NewsFetched -> {
                    newsAdapter.news.addAll(it.news)
                    newsAdapter.notifyDataSetChanged()
                }
                is ExampleZip1ViewModel.State.RecentlyViewedNewsFetched -> {
                    newsAdapter.recentlyViewedNews.clear()
                    newsAdapter.recentlyViewedNews.addAll(it.news)
                    newsAdapter.notifyDataSetChanged()
                }
                is ExampleZip1ViewModel.State.NoInternetConnection -> {
                    onShowNoInternetConnectionStateView()
                }
                is ExampleZip1ViewModel.State.UnknownError -> {
                    onShownUnknownErrorStateView()
                }
            }
        })
    }

    private fun onChangeLoadingState(loading: Boolean) {
        activityExample1SwipeRefreshLayout.isRefreshing = loading
        paginatedScrollListener.isLoading = loading
    }

    private fun onShownUnknownErrorStateView() {
        example1ActivityStateDisplay.show {
            image(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            title(R.string.error_unknown_error)
        }
    }

    private fun onShowNoInternetConnectionStateView() {
        example1ActivityStateDisplay.show {
            image(R.drawable.ic_signal_wifi_off_black_24dp)
            title(R.string.error_no_internet_connection)
        }
    }

    private fun fetchNews(page: Int = 0) {
        viewModelZip.fetchNews(page)
    }
}