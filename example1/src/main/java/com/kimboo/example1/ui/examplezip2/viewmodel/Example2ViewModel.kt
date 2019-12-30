package com.kimboo.example1.ui.examplezip2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimboo.interactors.GetProfileInteractor
import com.kimboo.models.BusinessSkills
import com.kimboo.models.ProfileInformation
import javax.inject.Inject

class Example2ViewModel @Inject constructor(
    private val getProfileInteractor: GetProfileInteractor
) : ViewModel(), GetProfileInteractor.Callback {

    sealed class State {
        data class ShowProfileInformation(
            val profile: ProfileInformation
        ) : State()
        data class ShowBusinessSkillsInformation(
            val businessSkills: BusinessSkills
        ) : State()
        object HideProfile : State()
        object HideBusiness : State()
    }

    sealed class StateMessage {
        object NoInternetConnection : StateMessage()
        object UnknownError : StateMessage()
        object UnauthorizedError : StateMessage()
    }

    val message = MutableLiveData<StateMessage>()
    val isLoading = MutableLiveData<Boolean>()
    val state = MutableLiveData<State>()

    // region GetProfileInteractor.Callback implementation
    override fun onProfileFetchedSuccessfully(
        profile: ProfileInformation?,
        businessSkills: BusinessSkills?
    ) {
        isLoading.value = false

        state.value = if (profile != null) {
            State.ShowProfileInformation(
                profile
            )
        } else {
            State.HideProfile
        }

        state.value = if (businessSkills != null) {
            State.ShowBusinessSkillsInformation(
                businessSkills
            )
        } else {
            State.HideBusiness
        }
    }

    override fun onErrorFetchingProfile() {
        isLoading.value = false
        state.value =
            State.HideProfile
    }

    override fun onErrorFetchingBusinessSkills() {
        isLoading.value = false
        state.value =
            State.HideBusiness
    }
    // endregion

    fun fetchProfile() {
        isLoading.value = true
        getProfileInteractor.execute(this)
    }
}