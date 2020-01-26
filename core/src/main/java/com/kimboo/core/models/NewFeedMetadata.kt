package com.kimboo.core.models

data class NewFeedMetadata(
    val list: List<NewFeed>,
    val current: Int,
    val total: Int
)