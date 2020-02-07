package com.kimboo.core.di.modules

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ImgurNetworkModule {
    @Provides
    @Named("authInterceptor")
    fun providesAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            request = request.newBuilder()
                .header("Authorization", "Client-ID 6ea78556ea84b48")
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    @Named("imgurOkHttp")
    fun provideOkHttpClient(
        @Named("authInterceptor") authInterceptor: Interceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(authInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    @Named("imgurRetrofit")
    fun provideRetrofit(
        @Named("imgurOkHttp") imgurOkHttp: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.imgur.com/3/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(imgurOkHttp)
            .build()
    }
}