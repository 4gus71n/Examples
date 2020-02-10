package com.kimboo.core.interactors

import com.kimboo.core.mappers.toBusinessSkills
import com.kimboo.core.mappers.toProfile
import com.kimboo.core.models.BusinessSkills
import com.kimboo.core.models.ProfileInformation
import com.kimboo.core.repositories.ProfileRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import java.net.ConnectException
import java.net.UnknownHostException

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

                onHandleErrors(profile, business, callback)
            },
            {
                callback.onUnknownError()
            }
        )
    }

    /**
     * Handles the errors from both response wrappers.
     */
    private fun onHandleErrors(
        profile: ResponseWrapper<ProfileInformation>,
        business: ResponseWrapper<BusinessSkills>,
        callback: GetProfileInteractor.Callback
    ) {
        when {
            // If API calls failed
            profile.exception != null &&  business.exception != null -> {
                val exception = profile.exception
                if (exception is ConnectException || exception is UnknownHostException) {
                    // If both API calls failed due to no internet connectivity
                    callback.onNoInternetConnection()
                } else {
                    // If both API calls failed for some other reason.
                    callback.onUnknownError()
                }
            }
            profile.exception != null -> {
                callback.onErrorFetchingProfile()
            }
            business.exception != null -> {
                callback.onErrorFetchingBusinessSkills()
            }
        }
    }

    /**
     * We zip both API calls together, the /business and the /profile.
     */
    private fun getProfileAndBusinessSkills(): Observable<Pair<ResponseWrapper<ProfileInformation>, ResponseWrapper<BusinessSkills>>> {
        return Observables.zip(
            fetchProfile(),
            fetchSkills()
        ) { profile, business ->
            // We wrap both responses into a Pair object and pass this to the Subscriber.
            Pair(profile, business)
        }
    }

    /**
     * Fetches the business skills information through the /skills endpoint.
     */
    private fun fetchSkills() = profileRepository.fetchBusinessSkils()
        // If successful it maps the response into ResponseWrapper
        .map {
            ResponseWrapper(
                response = it.response.toBusinessSkills()
            )
        }
        // If something goes wrong we map the exception into the ResponseWrapper.
        .onErrorReturn { exception ->
            ResponseWrapper(
                exception = exception
            )
        }

    /**
     * Fetches the profile information through the /profile endpoint.
     */
    private fun fetchProfile() = profileRepository.fetchProfileInformation()
        // If successful it maps the response into ResponseWrapper
        .map {
            ResponseWrapper(
                response = it.response.toProfile()
            )
        }
        // If something goes wrong we map the exception into the ResponseWrapper.
        .onErrorReturn { exception: Throwable ->
            ResponseWrapper(
                exception = exception
            )
        }

    /**
     * This ResponseWrapper class is basically a wrapper to carry around whatever exception we might
     * get through the endpoints.
     */
    class ResponseWrapper<RESPONSE : Any>(
        val exception: Throwable? = null,
        val response: RESPONSE? = null
    )
}
