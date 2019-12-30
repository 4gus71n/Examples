package com.kimboo.example1.di.modules

import androidx.lifecycle.ViewModel
import com.kimboo.example1.ui.examplezip1.viewmodel.ExampleZip1ViewModel
import com.kimboo.example1.ui.examplezip2.viewmodel.ExampleZip2ViewModel
import com.kimboo.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class Example1ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ExampleZip1ViewModel::class)
    abstract fun bindExample1ViewModel(
        exampleZip1ViewModel: ExampleZip1ViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExampleZip2ViewModel::class)
    abstract fun bindExample2ViewModel(
        exampleZip2ViewModel: ExampleZip2ViewModel
    ): ViewModel
}