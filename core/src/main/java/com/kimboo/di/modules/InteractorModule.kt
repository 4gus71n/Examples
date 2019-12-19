package com.kimboo.di.modules

import com.kimboo.interactors.GetNewsInteractor
import com.kimboo.interactors.GetNewsInteractorImpl
import com.kimboo.repositories.NewsRepository
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
}