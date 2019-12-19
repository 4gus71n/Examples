package com.kimboo.mappers

import com.kimboo.models.BusinessSkills
import com.kimboo.models.NewFeed
import com.kimboo.models.NewFeedMetadata
import com.kimboo.models.ProfileInformation
import com.kimboo.retrofit.responses.ApiBusinessSkilssResponse
import com.kimboo.retrofit.responses.ApiNewFeedListResponse
import com.kimboo.retrofit.responses.ApiNewFeedResponse
import com.kimboo.retrofit.responses.ApiProfileResponse

/**
 * Classes with extended functions to turn backend responses into model classes
 */

/**
 * Turns @{ApiNewFeedListResponse} into @{NewFeedMetadata}
 */
fun ApiNewFeedListResponse.toNewsFeedMetadata(): NewFeedMetadata {
    return NewFeedMetadata(
        total = totalPages ?: 0,
        current = currentPage ?: 0,
        list = feed?.map { it.toNewFeed() } ?: emptyList()
    )
}

/**
 * Turns @{ApiNewFeedResponse} into @{NewFeed}
 */
fun ApiNewFeedResponse.toNewFeed(): NewFeed {
    return NewFeed(
        title = title ?: "",
        description = description ?: ""
    )
}

fun ApiBusinessSkilssResponse.toBusinessSkills(): BusinessSkills {
    return BusinessSkills(
        languages = languages,
        yearsExperience = years.toInt(),
        career = career
    )
}

fun ApiProfileResponse.toProfile(): ProfileInformation {
    return ProfileInformation(
        firsName = firstName,
        lastName = lastName,
        email = email
    )
}
