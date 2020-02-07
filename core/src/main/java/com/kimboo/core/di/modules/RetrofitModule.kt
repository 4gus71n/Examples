package com.kimboo.core.di.modules

import com.kimboo.core.retrofit.api.ExampleApi
import com.kimboo.core.retrofit.api.ImgurApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideExampleApi(
        retrofit: Retrofit
    ): ExampleApi {
        return retrofit.create(ExampleApi::class.java)
    }

    @Provides
    @Singleton
    fun provideImgurApi(
        @Named("imgurRetrofit") imgurRetrofit: Retrofit
    ): ImgurApi {
        return imgurRetrofit.create(ImgurApi::class.java)
    }
}