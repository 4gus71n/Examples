package com.kimboo.example2.ui.examplecache1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimboo.interactors.GetAllRecipesInteractor
import com.kimboo.models.Recipe
import javax.inject.Inject

class ExampleCache1ViewModel @Inject constructor(
    private val getAllRecipesInteractor: GetAllRecipesInteractor
) : ViewModel(), GetAllRecipesInteractor.Callback {
    // region State declaration
    sealed class State {
        data class Success(
            val list: List<Recipe>
        ) : State()
        object UnknownError : State()
        object NoInternetConnection : State()
    }

    val state = MutableLiveData<State>()
    val isLoading = MutableLiveData<Boolean>()
    // endregion

    // region GetAllRecipesInteractor.Callback implementation
    override fun onSuccessfullyFetchedAllRecipes(recipes: List<Recipe>) {
        isLoading.value = false
        state.value = State.Success(
            list = recipes
        )
    }

    override fun onErrorFetchingRecipes() {
        isLoading.value = false
        state.value = State.UnknownError
    }

    fun fetchRecipes() {
        getAllRecipesInteractor.connect(this)
    }
    // endregion
}