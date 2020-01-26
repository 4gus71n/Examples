package com.kimboo.core.retrofit.responses

import com.google.gson.annotations.SerializedName

data class ApiNewFeedResponse(
    @SerializedName("articleTitle")
    val title: String?,
    @SerializedName("articleDescription")
    val description: String?
)