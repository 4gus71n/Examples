package com.kimboo.room.dto

import androidx.room.*

@Entity(
    tableName = "db_recipes_dto",
    indices = [Index(
        value = ["id"],
        unique = true
    )]
)
data class DbRecipesDto(
    @PrimaryKey
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val isBookmarked: Boolean = false
)