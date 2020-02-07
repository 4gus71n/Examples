package com.kimboo.core.retrofit.responses.imgur

import com.google.gson.annotations.SerializedName
import java.util.*

data class ApiCommentResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("datetime")
    val date: Date,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("image_id")
    val imageId: String?,
    @SerializedName("children")
    val children: List<ApiCommentResponse>?
)