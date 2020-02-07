package com.kimboo.example3.di.component

import com.kimboo.core.di.component.BaseSubComponent
import com.kimboo.example3.di.modules.Example3ViewModelModule
import com.kimboo.example3.ui.search.ExampleImgurActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [BaseSubComponent::class],
    modules = [Example3ViewModelModule::class]
)
interface Example3ViewInjector {
    fun inject(activity: ExampleImgurActivity)

    @Component.Builder
    interface Builder {
        fun baseSubComponent(baseSubComponent: BaseSubComponent): Builder
        fun build(): Example3ViewInjector
    }
}