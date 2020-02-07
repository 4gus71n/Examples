package com.kimboo.core.models.imgur

data class ImgurGallery(
    val id: String,
    val title: String,
    val commentCount: Int,
    val cover: String?,
    val images: List<ImgurImage>
) {
    val mediumCover: String by lazy {
        "https://i.imgur.com/${cover}m.jpg"
    }
}