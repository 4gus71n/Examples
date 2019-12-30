package com.kimboo.example1.di.modules

import androidx.lifecycle.ViewModel
import com.kimboo.example1.ui.examplezip1.viewmodel.Example1ViewModel
import com.kimboo.example1.ui.examplezip2.viewmodel.Example2ViewModel
import com.kimboo.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class Example1ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(Example1ViewModel::class)
    abstract fun bindExample1ViewModel(
        example1ViewModel: Example1ViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(Example2ViewModel::class)
    abstract fun bindExample2ViewModel(
        example2ViewModel: Example2ViewModel
    ): ViewModel
}