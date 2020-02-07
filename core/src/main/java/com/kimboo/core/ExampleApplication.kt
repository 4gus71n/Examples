package com.kimboo.core

import android.app.Application
import com.kimboo.core.di.component.AppComponent
import com.kimboo.core.di.component.DaggerAppComponent
import com.kimboo.core.di.modules.*

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
            .exampleNetworkModule(onBuildNetworkModule())
            .imgurNetworkModule(onBuildImgurNetworkModule())
            .repositoryModule(onBuildRepositoryModule())
            .retrofitModule(onBuildRetrofitModule())
            .roomModule(onBuildRoomModule())
            .build()
    }

    private fun onBuildImgurNetworkModule() = ImgurNetworkModule()

    private fun onBuildRoomModule() = RoomModule()

    private fun onBuildRetrofitModule() = RetrofitModule()

    private fun onBuildRepositoryModule() = RepositoryModule()

    private fun onBuildNetworkModule() = ExampleNetworkModule()

    private fun onBuildInteractorModule() = InteractorModule()

    private fun onBuildAppModule() = AppModule(applicationContext)
}