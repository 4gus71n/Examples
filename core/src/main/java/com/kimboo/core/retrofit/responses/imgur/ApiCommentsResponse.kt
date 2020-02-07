package com.kimboo.core.retrofit.responses.imgur

import com.google.gson.annotations.SerializedName
import com.kimboo.core.retrofit.responses.imgur.ApiCommentResponse

data class ApiCommentsResponse(
    @SerializedName("data")
    val data: List<ApiCommentResponse>?
)