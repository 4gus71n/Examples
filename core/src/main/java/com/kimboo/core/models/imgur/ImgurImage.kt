package com.kimboo.core.models.imgur

data class ImgurImage(
    val id: String,
    val commentCount: Int,
    private val imageUrl: String
) {
    val imageUrlMedium: String by lazy {
        "https://i.imgur.com/${imageUrl}m.jpg"
    }
}