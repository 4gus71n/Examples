package com.kimboo.retrofit.responses

import com.google.gson.annotations.SerializedName

data class ApiRecipeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("ingredients")
    val ingredients: List<String>
)