package com.kimboo.core.mappers

import com.kimboo.core.models.*
import com.kimboo.core.retrofit.responses.*
import com.kimboo.core.room.dto.DbRecipeDto

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

fun List<ApiRecipeResponse>.fromApiListToModelList(): List<Recipe> {
    return map { it.fromApiToModel() }
}

fun ApiRecipeResponse.fromApiToModel(): Recipe {
    return Recipe(
        id = id,
        name = name,
        ingredients = ingredients
    )
}

fun List<DbRecipeDto>.fromDbListToModelList(): List<Recipe> {
    return map { it.fromDbToModel() }
}

fun DbRecipeDto.fromDbToModel(): Recipe {
    return Recipe(
        id = id,
        name = name,
        ingredients = ingredients,
        isBookmarked = isBookmarked
    )
}

fun Recipe.fromModelToDb(): DbRecipeDto {
    return DbRecipeDto(
        id = id,
        name = name,
        ingredients = ingredients,
        isBookmarked = isBookmarked
    )
}

fun List<Recipe>.fromModelListToDbList(): List<DbRecipeDto> {
    return map { it.fromModelToDb() }
}
