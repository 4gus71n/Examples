package com.kimboo.core.retrofit.responses.imgur

import com.google.gson.annotations.SerializedName
import java.util.*

data class ApiImgurGalleryImageResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("datetime")
    val datetime: Date?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("animated")
    val animated: Boolean?,
    @SerializedName("width")
    val width: Int?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("size")
    val size: Int?,
    @SerializedName("views")
    val views: Int?,
    @SerializedName("vote")
    val vote: Boolean?,
    @SerializedName("favorite")
    val favorite: Boolean?,
    @SerializedName("nsfw")
    val nsfw: Boolean?,
    @SerializedName("section")
    val section: String?,
    @SerializedName("account_url")
    val accountUrl: String?,
    @SerializedName("account_id")
    val accountId: Int?,
    @SerializedName("is_ad")
    val isAd: Boolean?,
    @SerializedName("in_most_viral")
    val inMostViral: Boolean?,
    @SerializedName("has_sound")
    val hasSound: Boolean?,
    @SerializedName("tags")
    val tags: List<ApiImgurGalleryTagResponse>?,
    @SerializedName("ad_type")
    val adType: Int?,
    @SerializedName("ad_url")
    val adUrl: String?,
    @SerializedName("in_gallery")
    val inGallery: Boolean?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("mp4")
    val mp4: String?,
    @SerializedName("gifv")
    val gifv: String?,
    @SerializedName("mp4_size")
    val mp4Size: Int?,
    @SerializedName("looping")
    val looping: Boolean?,
    @SerializedName("comment_count")
    val commentCount: Int?,
    @SerializedName("favorite_count")
    val favoriteCount: Int?,
    @SerializedName("ups")
    val ups: Int?,
    @SerializedName("downs")
    val downs: Int?,
    @SerializedName("points")
    val points: Int?,
    @SerializedName("score")
    val score: Int?
)
