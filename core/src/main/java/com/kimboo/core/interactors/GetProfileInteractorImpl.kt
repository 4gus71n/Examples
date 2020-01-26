package com.kimboo.core.interactors

import com.kimboo.core.mappers.toBusinessSkills
import com.kimboo.core.mappers.toProfile
import com.kimboo.core.models.BusinessSkills
import com.kimboo.core.models.ProfileInformation
import com.kimboo.core.repositories.ProfileRepository
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables

class GetProfileInteractorImpl (
    private val profileRepository: ProfileRepository
) : GetProfileInteractor {
    override fun execute(callback: GetProfileInteractor.Callback) {
        getProfileAndBusinessSkills().subscribe(
            {
                val profile = it.first
                val business = it.second

                callback.onProfileFetchedSuccessfully(
                    profile = profile.response,
                    businessSkills = business.response
                )

                if (profile.exception != null) {
                    callback.onErrorFetchingProfile()
                }
                if (business.exception != null) {
                    callback.onErrorFetchingBusinessSkills()
                }
            },
            {
                callback.onUnknownError()
            }
        )
    }

    private fun getProfileAndBusinessSkills(): Observable<Pair<ResponseWrapper<ProfileInformation>, ResponseWrapper<BusinessSkills>>> {
        return Observables.zip(
            fetchProfile(),
            fetchSkills()
        ) { profile, business ->
            Pair(profile, business)
        }
    }

    private fun fetchSkills() = profileRepository.fetchBusinessSkils()
        .map {
            ResponseWrapper(
                response = it.response.toBusinessSkills()
            )
        }
        .onErrorReturn { exception ->
            ResponseWrapper(
                exception = exception
            )
        }

    private fun fetchProfile() = profileRepository.fetchProfileInformation()
        .map {
            ResponseWrapper(
                response = it.response.toProfile()
            )
        }
        .onErrorReturn { exception: Throwable ->
            ResponseWrapper(
                exception = exception
            )
        }

    class ResponseWrapper<RESPONSE : Any>(
        val exception: Throwable? = null,
        val response: RESPONSE? = null
    )
}
