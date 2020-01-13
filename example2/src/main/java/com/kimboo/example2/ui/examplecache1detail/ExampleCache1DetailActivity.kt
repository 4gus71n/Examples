package com.kimboo.example2.ui.examplecache1detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.appbar.AppBarLayout
import com.kimboo.example2.R
import com.kimboo.example2.di.component.DaggerExample2ViewInjector
import com.kimboo.example2.di.component.Example2ViewInjector
import com.kimboo.example2.ui.examplecache1detail.viewmodel.ExampleCache1DetailViewModel
import com.kimboo.models.Recipe
import com.kimboo.utils.MyViewModelFactory
import com.kimboo.utils.getBaseSubComponent
import kotlinx.android.synthetic.main.activity_example_cache_1_detail.*
import javax.inject.Inject
import kotlin.math.abs

class ExampleCache1DetailActivity : AppCompatActivity(){

    // region Variables declaration
    @Inject
    lateinit var viewModelProvider: MyViewModelFactory
    lateinit var viewModel: ExampleCache1DetailViewModel

    private val viewInjector: Example2ViewInjector
        get() = DaggerExample2ViewInjector.builder()
            .baseSubComponent(getBaseSubComponent())
            .build()
    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_cache_1_detail)

        setupToolbar()
        setupParallaxFadeOut()

        viewInjector.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelProvider)
            .get(ExampleCache1DetailViewModel::class.java)

        observeStateChanges()

        val recipeId = intent.extras?.let {
            it.getInt(ARG_BUNDLE_RECIPE_ID)
        } ?: run {
            throw RuntimeException("You need to pass the recipeId through the Bundle!")
        }

        viewModel.fetchRecipe(recipeId)
    }

    private fun observeStateChanges() {
        viewModel.state.observe(this, Observer {
            when (it) {
                is ExampleCache1DetailViewModel.State.Success -> {
                    onShowRecipe(it.recipe)
                }
                is ExampleCache1DetailViewModel.State.UnknownError -> {
                    onShowUnknownErrorSnackbar()
                }
            }
        })
    }

    private fun onShowUnknownErrorSnackbar() {
        // TODO
    }

    private fun onShowRecipe(recipe: Recipe) {
        activityExampleCache1DetailTitle.text = recipe.name
        activityExampleCache1DetailDescription.text = getString(
            R.string.ingredients_format_label,
            recipe.ingredients.joinToString(", ").plus(".")
        )
        invalidateOptionsMenu()
    }

    private fun setupToolbar() {
        setSupportActionBar(activityExampleCache1DetailToolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.exxample_cache1_detail_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val markRecipeItem = menu.findItem(R.id.action_bookmark)
        val unmarkRecipeItem = menu.findItem(R.id.action_unmark)
        markRecipeItem.isVisible = !viewModel.isRecipeBookmarked()
        unmarkRecipeItem.isVisible = viewModel.isRecipeBookmarked()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_unmark -> {
                viewModel.bookmarkRecipe()
                true
            }
            R.id.action_bookmark -> {
                viewModel.bookmarkRecipe()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun setupParallaxFadeOut() {
        activityExampleCache1DetailAppbarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            activityExampleCache1DetailDescriptionContainer.alpha = 1 - abs(
                verticalOffset.toFloat() / appBarLayout.totalScrollRange
            )
        })
    }

    companion object {
        private const val ARG_BUNDLE_RECIPE_ID = "arg_bundle_recipe_id"

        fun getStartIntent(context: Context, recipeId: Int): Intent {
            return Intent(context, ExampleCache1DetailActivity::class.java).apply {
                putExtra(ARG_BUNDLE_RECIPE_ID, recipeId)
            }
        }
    }
}