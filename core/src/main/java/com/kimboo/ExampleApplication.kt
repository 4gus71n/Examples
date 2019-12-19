package com.kimboo

import android.app.Application
import com.kimboo.di.component.AppComponent
import com.kimboo.di.component.DaggerAppComponent
import com.kimboo.di.modules.*

class ExampleApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = onBuildAppComponent()
    }

    private fun onBuildAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(onBuildAppModule())
            .interactorModule(onBuildInteractorModule())
            .networkModule(onBuildNetworkModule())
            .repositoryModule(onBuildRepositoryModule())
            .retrofitModule(onBuildRetrofitModule())
            .build()
    }

    private fun onBuildRetrofitModule() = RetrofitModule()

    private fun onBuildRepositoryModule() = RepositoryModule()

    private fun onBuildNetworkModule() = NetworkModule()

    private fun onBuildInteractorModule() = InteractorModule()

    private fun onBuildAppModule() = AppModule(applicationContext)
}