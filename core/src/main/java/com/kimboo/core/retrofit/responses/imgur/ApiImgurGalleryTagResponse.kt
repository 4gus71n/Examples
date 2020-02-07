package com.kimboo.core.retrofit.responses.imgur

import com.google.gson.annotations.SerializedName

data class ApiImgurGalleryTagResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("followers")
    val followers: Int?,
    @SerializedName("total_items")
    val totalItems: Int?,
    @SerializedName("following")
    val following: Boolean?,
    @SerializedName("background_hash")
    val backgroundHash: String?,
    @SerializedName("accent")
    val accent: String?,
    @SerializedName("background_is_animated")
    val backgroundIsAnimated: Boolean?,
    @SerializedName("thumbnail_is_animated")
    val thumbnailIsAnimated: Boolean?,
    @SerializedName("is_promoted")
    val isPromoted: Boolean?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("items")
    val items: List<ApiImgurGalleryPostResponse>?
)