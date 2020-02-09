package com.kimboo.core.interactors

import com.kimboo.core.mappers.fromApiListToModelList
import com.kimboo.core.mappers.fromDbListToModelList
import com.kimboo.core.mappers.fromModelListToDbList
import com.kimboo.core.repositories.RecipesCacheRepository
import com.kimboo.core.repositories.RecipesNetworkRepository
import com.kimboo.core.room.dto.DbRecipeDto
import com.kimboo.core.utils.DataResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

class GetAllRecipesInteractorImpl(
    private val recipesNetworkRepository: RecipesNetworkRepository,
    private val recipesCacheRepository: RecipesCacheRepository
) : GetAllRecipesInteractor {

    private val disposable = CompositeDisposable()

    override fun execute(callback: GetAllRecipesInteractor.Callback) {
        disposable.addAll(
            getRecipesFromCache(callback).subscribe(),
            getRecipesFromNetworkAndUpdateCache(callback).subscribe()
        )
    }

    private fun getRecipesFromNetworkAndUpdateCache(
        callback: GetAllRecipesInteractor.Callback
    ): Observable<DataResponse<Boolean>> {
        return recipesNetworkRepository.fetchRecepies()
            // Once we fetch the recipes from network we update the cache
            .flatMap {
                // Notice that if the API was to reply with a 304 (Not Modified) response
                // we can check that condition here and avoid performing an useless update on
                // the database cache.
                val fetchedRecipes = it.response.fromApiListToModelList()
                val storedRecipes = fetchedRecipes.fromModelListToDbList()
                recipesCacheRepository.storeRecipes(storedRecipes)
            }
            // If there's an error we handle that here
            .doOnError {
                if (it is ConnectException || it is UnknownHostException) {
                    callback.onNoInternetConnection()
                } else {
                    callback.onErrorFetchingRecipes()
                }
            }
            // If there's any error we return an empty observable so the error doesn't get handled by
            // Subscriber
            .onErrorResumeNext(Observable.empty())
            // We delay the subscription so we can be sure that we fetched the items from the
            // database cache. Same thing can be achieved through Observable#concatDelayError.
            .delaySubscription(1, TimeUnit.SECONDS)
    }

    /**
     * We fetch the recipes from the cache repository.
     */
    private fun getRecipesFromCache(
        callback: GetAllRecipesInteractor.Callback
    ): Observable<DataResponse<List<DbRecipeDto>>> {
        return recipesCacheRepository.getAllRecipes()
            // As soon as we fetch something from the database we use the callback to
            // communicate the results.
            .doOnNext {
                val recipes = it.response.fromDbListToModelList()
                callback.onSuccessfullyFetchedAllRecipes(recipes)
            }
            // If there's an error here we trigger the generic error callback. Notice that
            // if we wanted to show a special error message to the user when this happens we
            // could simply just create a new callback.
            .doOnError {
                callback.onErrorFetchingRecipes()
            }
    }

    override fun dispose() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}