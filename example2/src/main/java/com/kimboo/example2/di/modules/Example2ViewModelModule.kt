package com.kimboo.example2.di.modules

import androidx.lifecycle.ViewModel
import com.kimboo.example2.ui.viewmodel.Example2ViewModel
import com.kimboo.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class Example2ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(Example2ViewModel::class)
    abstract fun bindExample2ViewModel(
        example2ViewModel: Example2ViewModel
    ): ViewModel
}