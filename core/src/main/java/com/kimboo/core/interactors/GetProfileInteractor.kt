package com.kimboo.core.interactors

import com.kimboo.core.models.BusinessSkills
import com.kimboo.core.models.ProfileInformation

interface GetProfileInteractor {
    interface Callback {
        fun onProfileFetchedSuccessfully(
            profile: ProfileInformation?,
            businessSkills: BusinessSkills?
        )
        fun onErrorFetchingProfile()
        fun onErrorFetchingBusinessSkills()
        fun onUnknownError() {}
    }

    fun execute(callbakc: Callback)
}