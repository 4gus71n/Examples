package com.kimboo.example2.ui.examplecache1detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimboo.core.interactors.BookmarkRecipeInteractor
import com.kimboo.core.interactors.GetRecipeByIdInteractor
import com.kimboo.core.models.Recipe
import javax.inject.Inject

class ExampleCache1DetailViewModel @Inject constructor(
    private val bookmarkRecipeInteractor: BookmarkRecipeInteractor,
    private val getRecipeByIdInteractor: GetRecipeByIdInteractor
) : ViewModel(), BookmarkRecipeInteractor.Callback, GetRecipeByIdInteractor.Callback {

    // region State class declaration
    sealed class State {
        data class Success(
            val recipe: Recipe
        ) : State()
        object UnknownError : State()
    }

    val state = MutableLiveData<State>()

    private lateinit var _recipe: Recipe
    // endregion

    // region GetRecipeByIdInteractor.Callback implementation
    override fun onSuccessfullyFetchedRecipe(recipes: Recipe) {
        _recipe = recipes
        state.value = State.Success(recipes)
    }

    override fun onErrorFetchingRecipe() {
        state.value = State.UnknownError
    }
    // endregion

    // region BookmarkRecipeInteractor.Callback implementation
    override fun onSuccessfullyBookmarkedRecipe(isBookmarked: Boolean) {
        // Do nothing here, we don't care about this hook.
    }

    override fun onErrorFetchingRecipes() {
        state.value = State.UnknownError
    }
    // endregion

    fun bookmarkRecipe() {
        bookmarkRecipeInteractor.execute(_recipe.id, this)
    }

    fun fetchRecipe(recipeId: Int) {
        getRecipeByIdInteractor.connect(recipeId, this)
    }

    fun isRecipeBookmarked(): Boolean = _recipe.isBookmarked

}