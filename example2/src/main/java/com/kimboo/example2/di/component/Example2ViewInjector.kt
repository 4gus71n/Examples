package com.kimboo.example2.di.component

import com.kimboo.di.component.BaseSubComponent
import com.kimboo.example2.ui.Example2Activity
import com.kimboo.example2.di.modules.Example2ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [BaseSubComponent::class],
    modules = [Example2ViewModelModule::class]
)
interface Example2ViewInjector {
    fun inject(activity: Example2Activity)
    @Component.Builder
    interface Builder {
        fun baseSubComponent(baseSubComponent: BaseSubComponent): Builder
        fun build(): Example2ViewInjector
    }
}