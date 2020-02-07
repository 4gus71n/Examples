package com.kimboo.core.retrofit.responses.imgur

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiImgurGalleryResponse(
    @SerializedName("data")
    val data: List<ApiImgurGalleryPostResponse>?
)