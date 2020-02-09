package com.kimboo.example2.ui.examplecache1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimboo.core.interactors.GetAllRecipesInteractor
import com.kimboo.core.models.Recipe
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
        data class IsLoading(
            val loading: Boolean
        ) : State()
    }

    sealed class Message {
        object UnknownError : Message()
        object NoInternetConnection : Message()
    }
    // endregion

    // region Variables declaration
    private val _recipes = mutableListOf<Recipe>()

    val state = MutableLiveData<State>()
    val message = MutableLiveData<Message>()
    // endregion

    // region GetAllRecipesInteractor.Callback implementation
    override fun onSuccessfullyFetchedAllRecipes(recipes: List<Recipe>) {
        _recipes.clear()
        _recipes.addAll(recipes)

        state.value = State.IsLoading(false)

        state.value = State.Success(
            list = recipes
        )
    }

    override fun onErrorFetchingRecipes() {
        state.value = State.IsLoading(false)

        if (_recipes.isEmpty()) {
            state.value = State.UnknownError
        } else {
            message.value = Message.UnknownError
        }
    }

    override fun onNoInternetConnection() {
        state.value = State.IsLoading(false)

        if (_recipes.isEmpty()) {
            state.value = State.NoInternetConnection
        } else {
            message.value = Message.NoInternetConnection
        }
    }

    // endregion

    fun fetchRecipes() {
        state.value = State.IsLoading(true)
        getAllRecipesInteractor.execute(this)
    }
}