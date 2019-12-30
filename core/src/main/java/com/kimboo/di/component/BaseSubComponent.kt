package com.kimboo.di.component

import com.kimboo.interactors.GetNewsInteractor
import com.kimboo.interactors.GetProfileInteractor
import dagger.Subcomponent

/**
 * Exposes all the dependencies to the feature modules. We are supposed to expose only the interactors
 * here. We don't want the feature modules to know nothing about repositories, or any other unnecessary
 * dependency.
 */
@Subcomponent
interface BaseSubComponent {
    val getNewsInteractor: GetNewsInteractor
    val getProfileInteractor: GetProfileInteractor

    @Subcomponent.Builder
    interface Builder {
        fun build(): BaseSubComponent
    }
}