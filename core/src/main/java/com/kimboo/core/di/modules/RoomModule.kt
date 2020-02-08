package com.kimboo.core.di.modules

import android.content.Context
import com.kimboo.core.room.ExampleRoomDatabase
import com.kimboo.core.room.dao.RecipesDao
import dagger.Module
import dagger.Provides
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