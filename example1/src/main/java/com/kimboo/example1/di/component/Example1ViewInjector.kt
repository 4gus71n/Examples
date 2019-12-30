package com.kimboo.example1.di.component

import com.kimboo.di.component.BaseSubComponent
import com.kimboo.example1.di.modules.Example1ViewModelModule
import com.kimboo.example1.ui.examplezip1.Example1Activity
import com.kimboo.example1.ui.examplezip2.Example2Activity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [BaseSubComponent::class],
    modules = [Example1ViewModelModule::class]
)
interface Example1ViewInjector {

    fun inject(activity: Example1Activity)
    fun inject(activity: Example2Activity)

    @Component.Builder
    interface Builder {
        fun baseSubComponent(baseSubComponent: BaseSubComponent): Builder
        fun build(): Example1ViewInjector
    }
}