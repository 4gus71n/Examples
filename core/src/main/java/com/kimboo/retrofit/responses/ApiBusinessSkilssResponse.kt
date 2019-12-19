package com.kimboo.retrofit.responses

import com.google.gson.annotations.SerializedName

data class ApiBusinessSkilssResponse (
    @SerializedName("career")
    val career: String,
    @SerializedName("yearsOfExperience")
    val years: String,
    @SerializedName("languages")
    val languages: List<String>
)