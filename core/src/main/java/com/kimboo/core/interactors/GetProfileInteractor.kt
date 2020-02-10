package com.kimboo.core.interactors

import com.kimboo.core.models.BusinessSkills
import com.kimboo.core.models.ProfileInformation

interface GetProfileInteractor {
    interface Callback {
        /**
         * Callback triggered when we successfully fetch the profile and the business skills
         * information.
         */
        fun onProfileFetchedSuccessfully(
            profile: ProfileInformation?,
            businessSkills: BusinessSkills?
        )
        /**
         * Callback triggered when something goes wrong while trying to fetch the profile information
         * through the /profile. We either got a 401/403, 404 or a 500 from the backend endpoint.
         */
        fun onErrorFetchingProfile()
        /**
         * Callback triggered when something goes wrong while trying to fetch the business skills
         * through the /business. We either got a 401/403, 404 or a 500 from the backend endpoint.
         */
        fun onErrorFetchingBusinessSkills()
        /**
         * Callback triggered when both API calls failed due to some unknown error.
         */
        fun onUnknownError()
        /**
         * Callback triggered when we try to fetch the data from the backend without internet
         * connection.
         */
        fun onNoInternetConnection()
    }

    fun execute(callbakc: Callback)
}