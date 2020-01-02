package com.kimboo.di.modules

import android.content.Context
import com.kimboo.retrofit.api.ExampleApi
import com.kimboo.room.ExampleRoomDatabase
import com.kimboo.room.dao.RecipesDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun provideExampleRoomDatabase(context: Context): ExampleRoomDatabase {
        return ExampleRoomDatabase.create(context, false)
    }

    @Provides
    @Singleton
    fun provideRecipesDao(exampleRoomDatabase: ExampleRoomDatabase): RecipesDao {
        return exampleRoomDatabase.recipesDao()
    }
}