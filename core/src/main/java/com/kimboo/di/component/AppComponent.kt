package com.kimboo.di.component

import com.kimboo.di.modules.*
import dagger.Component
import javax.inject.Singleton

/**
 * Ties together all the dependencies provided by the different modules and expose them through the
 * BaseSubComponent.
 */
@Singleton
@Component(
    modules = [
        AppModule::class, InteractorModule::class, NetworkModule::class,
        RepositoryModule::class, RetrofitModule::class, ViewModelModule::class
    ]
)
interface AppComponent {
    fun build(): BaseSubComponent.Builder
}