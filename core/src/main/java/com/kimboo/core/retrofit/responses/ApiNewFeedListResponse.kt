package com.kimboo.core.retrofit.responses

import com.google.gson.annotations.SerializedName

data class ApiNewFeedListResponse(
    @SerializedName("feed")
    val feed: List<ApiNewFeedResponse>?,
    @SerializedName("currentPage")
    val currentPage: Int?,
    @SerializedName("totalPages")
    val totalPages: Int?
)