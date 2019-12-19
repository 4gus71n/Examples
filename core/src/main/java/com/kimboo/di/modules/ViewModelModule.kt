package com.kimboo.di.modules

import androidx.lifecycle.ViewModelProvider
import com.kimboo.utils.MyViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory
}