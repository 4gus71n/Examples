package com.kimboo.example3.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import com.ferfalk.simplesearchview.SimpleSearchView
import com.google.android.material.snackbar.Snackbar
import com.kimboo.core.models.imgur.ImgurGallery
import com.kimboo.core.utils.MyViewModelFactory
import com.kimboo.core.utils.getBaseSubComponent
import com.kimboo.example3.R
import com.kimboo.example3.di.component.DaggerExample3ViewInjector
import com.kimboo.example3.di.component.Example3ViewInjector
import com.kimboo.example3.ui.search.adapter.ImgurAdapter
import com.kimboo.example3.ui.search.viewmodel.ExampleImgurViewModel
import com.kimboo.example3.utils.PaginatedScrollListener
import kotlinx.android.synthetic.main.example_imgur_activity.*
import javax.inject.Inject

class ExampleImgurActivity : AppCompatActivity(), SimpleSearchView.OnQueryTextListener,
    Toolbar.OnMenuItemClickListener, PopupMenu.OnMenuItemClickListener {

    // region Variables declaration
    @Inject
    lateinit var viewModelProvider: MyViewModelFactory
    lateinit var viewModel: ExampleImgurViewModel

    private val adapter by lazy {
        ImgurAdapter(LayoutInflater.from(this))
    }

    private val paginatedScrollListener = object : PaginatedScrollListener() {
        override fun onLoadNextPage(page: Int) {
            viewModel.setPage(page)
        }
    }

    private val viewInjector: Example3ViewInjector
        get() = DaggerExample3ViewInjector.builder()
            .baseSubComponent(getBaseSubComponent())
            .build()
    // endregion

    // region Toolbar.OnMenuItemClickListener implementation
    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_imgur_window_day -> {
                viewModel.setWindow(ExampleImgurViewModel.SearchParams.Window.DAY)
                true
            }
            R.id.action_imgur_window_month -> {
                viewModel.setWindow(ExampleImgurViewModel.SearchParams.Window.MONTH)
                true
            }
            R.id.action_imgur_window_week -> {
                viewModel.setWindow(ExampleImgurViewModel.SearchParams.Window.WEEK)
                true
            }
            R.id.action_imgur_sort_top -> {
                viewModel.setSort(ExampleImgurViewModel.SearchParams.Sort.TOP)
                true
            }
            R.id.action_imgur_sort_viral -> {
                viewModel.setSort(ExampleImgurViewModel.SearchParams.Sort.VIRAL)
                true
            }
            else -> {
                false
            }
        }
    }
    // endregion

    // region SimpleSearchView.OnQueryTextListener implementation
    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.setQuery(query)
        activityExampleImgurSearchView.closeSearch(true)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        // Do nothing here, we don't care about this hook.
        return true
    }

    override fun onQueryTextCleared(): Boolean {
        // Do nothing here, we don't care about this hook
        return true
    }
    // endregion

    // region Lifecycle functions declaration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example_imgur_activity)

        viewInjector.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelProvider)
            .get(ExampleImgurViewModel::class.java)

        setupSwipeRefreshLayout()
        setupRecyclerView()
        setupToolbar()

        observeStateChanges()
        observeMessageChanges()
        observeQueryChanges()
        observeQuerySortChanges()
        observeQueryWindowChanges()
        observeSearchQueryChanges()
    }

    private fun observeSearchQueryChanges() {
        viewModel.query.observe(this, Observer {
            activityExampleImgurToolbar.title = it
        })
    }

    private fun observeQuerySortChanges() {
        viewModel.sort.observe(this, Observer {
            activityExampleImgurToolbar.menu.findItem(R.id.action_imgur_sort_top).isChecked = it == ExampleImgurViewModel.SearchParams.Sort.TOP
            activityExampleImgurToolbar.menu.findItem(R.id.action_imgur_sort_viral).isChecked = it == ExampleImgurViewModel.SearchParams.Sort.VIRAL
            // We can sort through a date windows only if the sort option is "TOP"
            activityExampleImgurToolbar.menu.findItem(R.id.action_imgur_window).isVisible = it == ExampleImgurViewModel.SearchParams.Sort.TOP
        })
    }

    private fun observeQueryWindowChanges() {
        viewModel.window.observe(this, Observer {
            activityExampleImgurToolbar.menu.findItem(R.id.action_imgur_window_week).isChecked = it == ExampleImgurViewModel.SearchParams.Window.WEEK
            activityExampleImgurToolbar.menu.findItem(R.id.action_imgur_window_month).isChecked = it == ExampleImgurViewModel.SearchParams.Window.MONTH
            activityExampleImgurToolbar.menu.findItem(R.id.action_imgur_window_day).isChecked = it == ExampleImgurViewModel.SearchParams.Window.DAY
        })
    }

    private fun observeQueryChanges() {
        viewModel.searchParams.observe(this, Observer {
            viewModel.searchPosts(it)
        })
    }

    private fun observeMessageChanges() {
        viewModel.message.observe(this, Observer {
            when (it) {
                is ExampleImgurViewModel.Message.NoInternetError -> {
                    onShowNoInternetConnectionSnackbar()
                }
                is ExampleImgurViewModel.Message.UnknownError -> {
                    onShowUnknownErrorSnackbar()
                }
            }
        })
    }

    private fun onShowUnknownErrorSnackbar() {
        Snackbar.make(
            activityExampleImgurContainer,
            getString(R.string.error_unknown_error),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun onShowNoInternetConnectionSnackbar() {
        Snackbar.make(
            activityExampleImgurContainer,
            getString(R.string.error_no_internet_connection),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun observeStateChanges() {
        viewModel.state.observe(this, Observer {
            when (it) {
                is ExampleImgurViewModel.State.IsLoading -> {
                    onLoadingStateChanged(it.isLoading)
                }
                is ExampleImgurViewModel.State.NoInternetError -> {
                    onShowNoInternetConnectionError()
                }
                is ExampleImgurViewModel.State.UnknownError -> {
                    onShownUnknownErrorStateView()
                }
                is ExampleImgurViewModel.State.Success -> {
                    if (it.list.isEmpty()) {
                        onShowNoResultsStateView(it.query)
                    } else {
                        onShowImgurPosts(it.list)
                    }
                }
            }
        })
    }

    private fun onShowImgurPosts(list: List<ImgurGallery>) {
        activityExampleImgurStateDisplay.hide()
        adapter.imgurPosts.clear()
        adapter.imgurPosts.addAll(list)
        adapter.notifyDataSetChanged()
    }

    private fun todo(list: List<ImgurGallery>) {
        DiffUtil.calculateDiff(adapter.getDiffUtilsCallback(list), true).apply {
            adapter.imgurPosts.clear()
            adapter.imgurPosts.addAll(list)
            dispatchUpdatesTo(adapter)
        }
    }

    private fun onShownUnknownErrorStateView() {
        activityExampleImgurStateDisplay.show {
            image(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            title(R.string.error_unknown_error)
        }
    }

    private fun onShowNoResultsStateView(query: String) {
        activityExampleImgurStateDisplay.show {
            image(R.drawable.ic_youtube_searched_for_black_24dp)
            title(getString(R.string.error_no_search_result_found, query))
        }
    }

    private fun onShowNoInternetConnectionError() {
        activityExampleImgurStateDisplay.show {
            image(R.drawable.ic_signal_wifi_off_black_24dp)
            title(R.string.error_no_internet_connection)
        }
    }

    private fun onLoadingStateChanged(loading: Boolean) {
        // First time that we start to load something, get rid of the description text.
        activityExampleImgurDescription.visibility = View.GONE
        paginatedScrollListener.isLoading = loading
        activityExampleImgurSwipeRefreshLayout.isRefreshing = loading
    }

    private fun setupToolbar() {
        activityExampleImgurToolbar.inflateMenu(R.menu.imgur_search_menu)
        val menuItem = activityExampleImgurToolbar.menu.findItem(R.id.action_imgur_search)
        activityExampleImgurSearchView.setMenuItem(menuItem)
        activityExampleImgurSearchView.setOnQueryTextListener(this)
        activityExampleImgurToolbar.setOnMenuItemClickListener(this)
    }

    private fun setupRecyclerView() {
        activityExampleImgurRecyclerView.adapter = adapter
        activityExampleImgurRecyclerView.addOnScrollListener(paginatedScrollListener)
    }

    private fun setupSwipeRefreshLayout() {
        activityExampleImgurSwipeRefreshLayout.setOnRefreshListener {
            viewModel.setPage(1)
        }
    }
    // endregion

}