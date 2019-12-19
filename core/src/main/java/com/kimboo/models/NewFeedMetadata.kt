package com.kimboo.models

data class NewFeedMetadata(
    val list: List<NewFeed>,
    val current: Int,
    val total: Int
)