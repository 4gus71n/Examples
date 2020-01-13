package com.kimboo.example2.di.modules

import androidx.lifecycle.ViewModel
import com.kimboo.example2.ui.examplecache1.viewmodel.ExampleCache1ViewModel
import com.kimboo.example2.ui.examplecache1detail.viewmodel.ExampleCache1DetailViewModel
import com.kimboo.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class Example2ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ExampleCache1ViewModel::class)
    abstract fun bindExampleCache1ViewModel(
        exampleCache1ViewModel: ExampleCache1ViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExampleCache1DetailViewModel::class)
    abstract fun bindExampleCache1DetailViewModel(
        exampleCache1DetailViewModel: ExampleCache1DetailViewModel
    ): ViewModel
}