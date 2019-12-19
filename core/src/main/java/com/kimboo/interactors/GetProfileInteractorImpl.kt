package com.kimboo.interactors

import com.kimboo.mappers.toBusinessSkills
import com.kimboo.mappers.toProfile
import com.kimboo.models.BusinessSkills
import com.kimboo.models.ProfileInformation
import com.kimboo.repositories.ProfileRepository
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import java.lang.Exception

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
