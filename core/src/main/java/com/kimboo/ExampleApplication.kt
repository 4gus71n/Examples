package com.kimboo

import android.app.Application
import com.kimboo.di.component.AppComponent
import com.kimboo.di.component.DaggerAppComponent
import com.kimboo.di.modules.*
import com.kimboo.room.ExampleRoomDatabase

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
            .roomModule(onBuildRoomModule())
            .build()
    }

    private fun onBuildRoomModule() = RoomModule()

    private fun onBuildRetrofitModule() = RetrofitModule()

    private fun onBuildRepositoryModule() = RepositoryModule()

    private fun onBuildNetworkModule() = NetworkModule()

    private fun onBuildInteractorModule() = InteractorModule()

    private fun onBuildAppModule() = AppModule(applicationContext)
}