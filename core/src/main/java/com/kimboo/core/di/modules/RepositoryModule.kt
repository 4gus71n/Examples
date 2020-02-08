package com.kimboo.core.di.modules

import com.kimboo.core.retrofit.api.ExampleApi
import com.kimboo.core.room.dao.RecipesDao
import com.kimboo.core.repositories.*
import com.kimboo.core.repositories.imgur.ApiImgurGalleryRepository
import com.kimboo.core.repositories.imgur.ApiImgurGalleryRepositoryImpl
import com.kimboo.core.retrofit.api.ImgurApi
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
class RepositoryModule {
    @Provides
    fun provideApiImgurGalleryRepository(
        @Named("uiScheduler") uiScheduler: Scheduler,
        @Named("backgroundScheduler") backgroundScheduler: Scheduler,
        imgurApi: ImgurApi
    ) : ApiImgurGalleryRepository {
        return ApiImgurGalleryRepositoryImpl(
            uiScheduler = uiScheduler,
            backgroundScheduler = backgroundScheduler,
            imgurApi = imgurApi
        )
    }

    @Provides
    fun provideNewsRepository(
        @Named("uiScheduler") uiScheduler: Scheduler,
        @Named("backgroundScheduler") backgroundScheduler: Scheduler,
        exampleApi: ExampleApi
    ) : NewsRepository {
        return NewsNetworkRepository(
            uiScheduler = uiScheduler,
            backgroundScheduler = backgroundScheduler,
            retrofitApi = exampleApi
        )
    }

    @Provides
    fun provideProfileRepository(
        @Named("uiScheduler") uiScheduler: Scheduler,
        @Named("backgroundScheduler") backgroundScheduler: Scheduler,
        exampleApi: ExampleApi
    ) : ProfileRepository {
        return ProfileNetworkRepository(
            uiScheduler = uiScheduler,
            backgroundScheduler = backgroundScheduler,
            retrofitApi = exampleApi
        )
    }

    @Provides
    fun provideRecepiesNetworkRepository(
        @Named("uiScheduler") uiScheduler: Scheduler,
        @Named("backgroundScheduler") backgroundScheduler: Scheduler,
        exampleApi: ExampleApi
    ) : RecipesNetworkRepository {
        return RecipesNetworkRepositoryImpl(
            uiScheduler = uiScheduler,
            backgroundScheduler = backgroundScheduler,
            retrofitApi = exampleApi
        )
    }

    @Provides
    fun provideRecepiesCacheRepository(
        @Named("uiScheduler") uiScheduler: Scheduler,
        @Named("backgroundScheduler") backgroundScheduler: Scheduler,
        recipesDao: RecipesDao
    ) : RecipesCacheRepository {
        return RecipesCacheRepositoryImpl(
            uiScheduler = uiScheduler,
            backgroundScheduler = backgroundScheduler,
            recepiesDao = recipesDao
        )
    }
}