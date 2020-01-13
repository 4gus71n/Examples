package com.kimboo.di.component

import com.kimboo.interactors.*
import dagger.Subcomponent

/**
 * Exposes all the dependencies to the feature modules. We are supposed to expose only the interactors
 * here. We don't want the feature modules to know nothing about repositories, or any other unnecessary
 * dependency.
 */
@Subcomponent
interface BaseSubComponent {
    val getAllRecipesInteractor: GetAllRecipesInteractor
    val getRecipeByIdInteractor: GetRecipeByIdInteractor
    val bookmarkRecipeInteractor: BookmarkRecipeInteractor
    val getNewsInteractor: GetNewsInteractor
    val getProfileInteractor: GetProfileInteractor

    @Subcomponent.Builder
    interface Builder {
        fun build(): BaseSubComponent
    }
}