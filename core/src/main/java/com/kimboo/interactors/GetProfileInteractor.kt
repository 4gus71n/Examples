package com.kimboo.interactors

import com.kimboo.models.BusinessSkills
import com.kimboo.models.ProfileInformation

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