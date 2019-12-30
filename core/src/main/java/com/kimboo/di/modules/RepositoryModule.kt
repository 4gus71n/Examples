package com.kimboo.di.modules

import com.kimboo.repositories.NewsNetworkRepository
import com.kimboo.repositories.NewsRepository
import com.kimboo.repositories.ProfileNetworkRepository
import com.kimboo.repositories.ProfileRepository
import com.kimboo.retrofit.api.ExampleApi
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
class RepositoryModule {
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
}