package com.kimboo.core.retrofit.responses.imgur

import com.google.gson.annotations.SerializedName

data class ApiImgurGalleryPostResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("datetime")
    val datetime: Int?,
    @SerializedName("cover")
    val cover: String?,
    @SerializedName("cover_width")
    val coverWidth: Int?,
    @SerializedName("cover_height")
    val coverHeight: Int?,
    @SerializedName("account_url")
    val accountUrl: String?,
    @SerializedName("account_id")
    val accountId: Int?,
    @SerializedName("privacy")
    val privacy: String?,
    @SerializedName("layout")
    val layout: String?,
    @SerializedName("views")
    val views: Int?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("ups")
    val ups: Int?,
    @SerializedName("downs")
    val downs: Int?,
    @SerializedName("points")
    val points: Int?,
    @SerializedName("score")
    val score: Int?,
    @SerializedName("is_album")
    val isAlbum: Boolean?,
    @SerializedName("vote")
    val vote: Boolean?,
    @SerializedName("favorite")
    val favorite: Boolean?,
    @SerializedName("nsfw")
    val nsfw: Boolean?,
    @SerializedName("section")
    val section: String?,
    @SerializedName("comment_count")
    val commentCount: Int?,
    @SerializedName("favorite_count")
    val favoriteCount: Int?,
    @SerializedName("topic")
    val topic: String?,
    @SerializedName("topic_id")
    val topicId: Int?,
    @SerializedName("images_count")
    val imagesCount: Int?,
    @SerializedName("in_gallery")
    val inGallery: Boolean?,
    @SerializedName("is_ad")
    val isAd: Boolean?,
    @SerializedName("tags")
    val tags: List<ApiImgurGalleryTagResponse>?,
    @SerializedName("in_most_viral")
    val inMostViral: Boolean?,
    @SerializedName("images")
    val images: List<ApiImgurGalleryImageResponse>?
)