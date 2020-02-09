package com.kimboo.example2.ui.examplecache1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.kimboo.example2.R
import com.kimboo.example2.di.component.DaggerExample2ViewInjector
import com.kimboo.example2.di.component.Example2ViewInjector
import com.kimboo.example2.ui.examplecache1.adapter.RecipesAdapter
import com.kimboo.example2.ui.examplecache1.viewmodel.ExampleCache1ViewModel
import com.kimboo.example2.ui.examplecache1detail.ExampleCache1DetailActivity
import com.kimboo.core.models.Recipe
import com.kimboo.core.utils.MyViewModelFactory
import com.kimboo.core.utils.getBaseSubComponent
import kotlinx.android.synthetic.main.activity_example_cache_1.*
import javax.inject.Inject
import kotlin.math.abs

class ExampleCache1Activity : AppCompatActivity(), RecipesAdapter.Callback {

    // region Variables declaration
    @Inject
    lateinit var viewModelProvider: MyViewModelFactory
    lateinit var viewModel: ExampleCache1ViewModel

    private val adapter by lazy {
        RecipesAdapter(this)
    }

    private val viewInjector: Example2ViewInjector
         get() = DaggerExample2ViewInjector.builder()
             .baseSubComponent(getBaseSubComponent())
             .build()
    // endregion

    // region RecipesAdapter.Callback implementation
    override fun onRecipeClicked(recipe: Recipe) {
        startActivity(ExampleCache1DetailActivity.getStartIntent(this, recipe.id))
    }
    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_cache_1)

        setupSwipeToRefreshLayout()
        setupParallaxFadeOut()
        setupToolbar()
        setupRecyclerView()

        viewInjector.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelProvider)
            .get(ExampleCache1ViewModel::class.java)

        observeStateChanges()
        observeMessageChanges()

        viewModel.fetchRecipes()
    }

    private fun observeMessageChanges() {
        viewModel.message.observe(this, Observer {
            when (it) {
                is ExampleCache1ViewModel.Message.UnknownError -> {
                    onShowUnknownErrorSnackbar()
                }
                is ExampleCache1ViewModel.Message.NoInternetConnection -> {
                    onShowNoInternetConnectionSnackbar()
                }
            }
        })
    }

    private fun onShowUnknownErrorSnackbar() {
        Snackbar.make(
            activityExampleCache1Container,
            getString(R.string.error_unknown_error),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun onShowNoInternetConnectionSnackbar() {
        Snackbar.make(
            activityExampleCache1Container,
            getString(R.string.error_no_internet_connection),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun observeStateChanges() {
        viewModel.state.observe(this, Observer {
            when (it) {
                is ExampleCache1ViewModel.State.Success -> {
                    onShowRecipes(it.list)
                }
                is ExampleCache1ViewModel.State.NoInternetConnection -> {
                    onShowNoInternetConnectionError()
                }
                is ExampleCache1ViewModel.State.UnknownError -> {
                    onShownUnknownErrorStateView()
                }
                is ExampleCache1ViewModel.State.IsLoading -> {
                    onChangeLoadingState(it.loading)
                }
            }
        })
    }

    private fun onChangeLoadingState(loading: Boolean) {
        activityExampleCache1SwipeRefreshLayout.isRefreshing = loading
    }

    private fun onShownUnknownErrorStateView() {
        activityExampleCache1RecipesStateDisplay.show {
            image(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            title(R.string.error_unknown_error)
        }
    }

    private fun onShowNoInternetConnectionError() {
        activityExampleCache1RecipesStateDisplay.show {
            image(R.drawable.ic_signal_wifi_off_black_24dp)
            title(R.string.error_no_internet_connection)
        }
    }

    private fun onShowRecipes(list: List<Recipe>) {
        activityExampleCache1RecipesStateDisplay.hide()
        adapter.recipes.clear()
        adapter.recipes.addAll(list)
        adapter.notifyDataSetChanged()
    }

    private fun setupRecyclerView() {
        activityExampleCache1RecipesReceyclerView.adapter = adapter
    }

    private fun setupToolbar() {
        setSupportActionBar(activityExampleCache1Toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupParallaxFadeOut() {
        activityExampleCache1AppbarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            activityExampleCache1DescriptionContainer.alpha = 1 - abs(
                verticalOffset.toFloat() / appBarLayout.totalScrollRange
            )
        })
    }

    private fun setupSwipeToRefreshLayout() {
        activityExampleCache1SwipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchRecipes()
        }
    }
}