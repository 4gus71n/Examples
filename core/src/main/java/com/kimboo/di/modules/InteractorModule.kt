package com.kimboo.di.modules

import com.kimboo.interactors.*
import com.kimboo.repositories.NewsRepository
import com.kimboo.repositories.ProfileRepository
import com.kimboo.repositories.RecipesCacheRepository
import com.kimboo.repositories.RecipesNetworkRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {
    @Provides
    fun provideGetNewsInteractor(
        newsRepository: NewsRepository
    ) : GetNewsInteractor {
        return GetNewsInteractorImpl(
            newsRepository = newsRepository
        )
    }

    @Provides
    fun provideGetProfileInteractor(
        profileRepository: ProfileRepository
    ) : GetProfileInteractor {
        return GetProfileInteractorImpl(
            profileRepository = profileRepository
        )
    }

    @Provides
    fun provideGetRecipeByIdInteractor(
        recipesCacheRepository: RecipesCacheRepository
    ) : GetRecipeByIdInteractor {
        return GetRecipeByIdInteractorImpl(
            recipesCacheRepository = recipesCacheRepository
        )
    }

    @Provides
    fun provideBookmarkRecipeInteractor(
        recipesCacheRepository: RecipesCacheRepository
    ) : BookmarkRecipeInteractor {
        return BookmarkRecipeInteractorImpl(
            recipesCacheRepository = recipesCacheRepository
        )
    }

    @Provides
    fun provideGetAllRecipesInteractor(
        recipesNetworkRepository: RecipesNetworkRepository,
        recipesCacheRepository: RecipesCacheRepository
    ) : GetAllRecipesInteractor {
        return GetAllRecipesInteractorImpl(
            recipesNetworkRepository= recipesNetworkRepository,
            recipesCacheRepository = recipesCacheRepository
        )
    }
}