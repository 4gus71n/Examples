package com.kimboo.core.retrofit.responses

import com.google.gson.annotations.SerializedName

data class ApiProfileResponse(
    @SerializedName("firstname")
    val firstName: String,
    @SerializedName("lastname")
    val lastName: String,
    @SerializedName("emailAddress")
    val email: String
)