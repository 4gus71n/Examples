package com.kimboo.example1.ui.examplezip2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimboo.core.interactors.GetProfileInteractor
import com.kimboo.core.models.BusinessSkills
import com.kimboo.core.models.ProfileInformation
import javax.inject.Inject

class ExampleZip2ViewModel @Inject constructor(
    private val getProfileInteractor: GetProfileInteractor
) : ViewModel(), GetProfileInteractor.Callback {

    // region Sealed classes declaration
    sealed class State {
        data class ShowProfileInformation(
            val profile: ProfileInformation
        ) : State()
        data class ShowBusinessSkillsInformation(
            val businessSkills: BusinessSkills
        ) : State()
        data class IsLoading(
            val loading: Boolean
        ) : State()
        object HideProfile : State()
        object HideBusiness : State()
        object NoInternetConnection : State()
        object UnknownError : State()
    }

    sealed class Message {
        object NoInternetConnection : Message()
        object UnknownError : Message()
    }
    // endregion

    // region Variables declaration
    val message = MutableLiveData<Message>()
    val state = MutableLiveData<State>()
    private var _profile: ProfileInformation? = null
    private var _businessSkills: BusinessSkills? = null
    // endregion

    // region GetProfileInteractor.Callback implementation
    override fun onProfileFetchedSuccessfully(
        profile: ProfileInformation?,
        businessSkills: BusinessSkills?
    ) {
        _profile = profile
        _businessSkills = businessSkills

        state.value = State.IsLoading(false)

        if (profile != null) {
            state.value = State.ShowProfileInformation(profile)
        }

        if (businessSkills != null) {
            state.value = State.ShowBusinessSkillsInformation(businessSkills)
        }
    }

    override fun onErrorFetchingProfile() {
        state.value = State.IsLoading(false)
        state.value = State.HideProfile
    }

    override fun onErrorFetchingBusinessSkills() {
        state.value = State.IsLoading(false)
        state.value = State.HideBusiness
    }

    override fun onUnknownError() {
        state.value = State.IsLoading(false)
        if (_businessSkills == null && _profile == null) {
            // If we haven't fetched the user information display the full error state
            state.value = State.UnknownError
        } else {
            // If we have fetched the user information show the error through a Snackbar.
            message.value = Message.UnknownError
        }
    }

    override fun onNoInternetConnection() {
        state.value = State.IsLoading(false)
        if (_businessSkills == null && _profile == null) {
            // If we haven't fetched the user information display the full error state
            state.value = State.NoInternetConnection
        } else {
            // If we have fetched the user information show the error through a Snackbar.
            message.value = Message.NoInternetConnection
        }
    }

    // endregion

    fun fetchProfile() {
        state.value = State.IsLoading(true)
        getProfileInteractor.execute(this)
    }
}