package com.kimboo.example1.di.modules

import androidx.lifecycle.ViewModel
import com.kimboo.example1.ui.viewmodel.Example1ViewModel
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
}