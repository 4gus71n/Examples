package com.kimboo.core.models.imgur

import java.util.*

data class ImgurComment(
    val id: Int,
    val replying: String?,
    val comment: String,
    val author: String?,
    val children: List<ImgurComment>,
    val date: Date,
    private val imageId: String?
) {
    val attachedImageUrl: String?
        get() {
            return imageId?.let {
                "https://i.imgur.com/${it}m.jpg"
            }
        }
}