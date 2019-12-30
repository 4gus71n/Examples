package com.kimboo.example1.di.component

import com.kimboo.di.component.BaseSubComponent
import com.kimboo.example1.di.modules.Example1ViewModelModule
import com.kimboo.example1.ui.examplezip1.ExampleZip1Activity
import com.kimboo.example1.ui.examplezip2.ExampleZip2Activity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [BaseSubComponent::class],
    modules = [Example1ViewModelModule::class]
)
interface Example1ViewInjector {

    fun inject(activityZip: ExampleZip1Activity)
    fun inject(activityZip: ExampleZip2Activity)

    @Component.Builder
    interface Builder {
        fun baseSubComponent(baseSubComponent: BaseSubComponent): Builder
        fun build(): Example1ViewInjector
    }
}