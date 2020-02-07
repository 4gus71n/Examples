package com.kimboo.example3.di.modules

import androidx.lifecycle.ViewModel
import com.kimboo.core.utils.ViewModelKey
import com.kimboo.example3.ui.search.viewmodel.ExampleImgurViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class Example3ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ExampleImgurViewModel::class)
    abstract fun bindExampleImgurViewModel(
        exampleImgurViewModel: ExampleImgurViewModel
    ): ViewModel
}