package com.kimboo.example2.di.component

import com.kimboo.core.di.component.BaseSubComponent
import com.kimboo.example2.di.modules.Example2ViewModelModule
import com.kimboo.example2.ui.examplecache1.ExampleCache1Activity
import com.kimboo.example2.ui.examplecache1detail.ExampleCache1DetailActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [BaseSubComponent::class],
    modules = [Example2ViewModelModule::class]
)
interface Example2ViewInjector {
    fun inject(activity: ExampleCache1Activity)
    fun inject(activity: ExampleCache1DetailActivity)

    @Component.Builder
    interface Builder {
        fun baseSubComponent(baseSubComponent: BaseSubComponent): Builder
        fun build(): Example2ViewInjector
    }
}