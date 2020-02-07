package com.kimboo.core.di.component

import com.kimboo.core.di.modules.*
import dagger.Component
import javax.inject.Singleton

/**
 * Ties together all the dependencies provided by the different modules and expose them through the
 * BaseSubComponent.
 */
@Singleton
@Component(
    modules = [
        AppModule::class, InteractorModule::class, ExampleNetworkModule::class,
        RepositoryModule::class, RetrofitModule::class, ViewModelModule::class,
        RoomModule::class, ImgurNetworkModule::class
    ]
)
interface AppComponent {
    fun build(): BaseSubComponent.Builder
}