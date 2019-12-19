package com.kimboo.example1.di.component

import com.kimboo.di.component.BaseSubComponent
import com.kimboo.example1.di.modules.Example1ViewModelModule
import com.kimboo.example1.ui.Example1Activity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [BaseSubComponent::class],
    modules = [Example1ViewModelModule::class]
)
interface Example1ViewInjector {
    fun inject(activity: Example1Activity)
    @Component.Builder
    interface Builder {
        fun baseSubComponent(baseSubComponent: BaseSubComponent): Builder
        fun build(): Example1ViewInjector
    }
}