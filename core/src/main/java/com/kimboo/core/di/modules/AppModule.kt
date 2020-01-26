package com.kimboo.core.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

/**
 * Provides all the app related dependencies. Context, Schedulers, SharedPreferences, etc.
 */
@Module
class AppModule (private val context: Context) {
    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    @Named("uiScheduler")
    fun provideUiScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @Singleton
    @Named("backgroundScheduler")
    fun provideBackgroundScheduler(): Scheduler {
        return Schedulers.io()
    }
}