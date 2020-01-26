package com.kimboo.core.di.modules

import androidx.lifecycle.ViewModelProvider
import com.kimboo.core.utils.MyViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory
}