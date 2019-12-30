package com.kimboo.di.modules

import com.kimboo.interactors.GetNewsInteractor
import com.kimboo.interactors.GetNewsInteractorImpl
import com.kimboo.interactors.GetProfileInteractor
import com.kimboo.interactors.GetProfileInteractorImpl
import com.kimboo.repositories.NewsRepository
import com.kimboo.repositories.ProfileRepository
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
}