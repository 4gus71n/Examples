package com.kimboo.core.di.modules

import com.kimboo.core.interactors.*
import com.kimboo.core.repositories.NewsRepository
import com.kimboo.core.repositories.ProfileRepository
import com.kimboo.core.repositories.RecipesCacheRepository
import com.kimboo.core.repositories.RecipesNetworkRepository
import com.kimboo.core.repositories.imgur.ApiImgurGalleryRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {
    @Provides
    fun provideGetImgurPostsInteractor(
        apiImgurGalleryRepository: ApiImgurGalleryRepository
    ) : GetImgurPostsInteractor {
        return GetImgurPostsInteractorImpl(
            apiImgurGalleryRepository
        )
    }

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
            recipesNetworkRepository = recipesNetworkRepository,
            recipesCacheRepository = recipesCacheRepository
        )
    }
}