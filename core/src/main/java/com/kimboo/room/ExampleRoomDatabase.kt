package com.kimboo.room

import android.content.Context
import androidx.room.*
import com.kimboo.room.dao.RecipesDao
import com.kimboo.room.dto.DbRecipeDto

@Database(
    entities = [DbRecipeDto::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ExampleRoomDatabase : RoomDatabase() {
    companion object {
        fun create(context: Context, useInMemory: Boolean): ExampleRoomDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, ExampleRoomDatabase::class.java)
            } else {
                Room.databaseBuilder(context, ExampleRoomDatabase::class.java, "example.db")
            }
            return databaseBuilder
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun recipesDao(): RecipesDao
}

class Converters {
    @TypeConverter
    fun toListOfStrings(flatStringList: String): List<String> {
        return flatStringList.split(",")
    }
    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>): String {
        return listOfString.joinToString(",")
    }
}