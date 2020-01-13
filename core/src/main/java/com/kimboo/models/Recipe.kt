package com.kimboo.models

data class Recipe(
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val isBookmarked: Boolean = false
)