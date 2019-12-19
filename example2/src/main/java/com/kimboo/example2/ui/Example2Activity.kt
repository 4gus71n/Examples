package com.kimboo.example2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kimboo.example1.ui.viewmodel.Example1ViewModel
import com.kimboo.example2.R
import com.kimboo.example2.di.component.DaggerExample2ViewInjector
import com.kimboo.example2.di.component.Example2ViewInjector
import com.kimboo.example2.ui.viewmodel.Example2ViewModel
import com.kimboo.models.BusinessSkills
import com.kimboo.models.ProfileInformation
import com.kimboo.utils.MyViewModelFactory
import com.kimboo.utils.getBaseSubComponent
import kotlinx.android.synthetic.main.activity_example_2.*
import javax.inject.Inject

class Example2Activity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: MyViewModelFactory
    lateinit var viewModel: Example2ViewModel

    private val viewInjector: Example2ViewInjector
         get() = DaggerExample2ViewInjector.builder()
             .baseSubComponent(getBaseSubComponent())
             .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_2)

        setupSwipeToRefreshLayout()

        viewInjector.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelProvider)
            .get(Example2ViewModel::class.java)

        observeStateChanges()
        observeLoadingChanges()
        observeMessageChanges()

        viewModel.fetchProfile()
    }

    /**
     * Observes the message changes from the ViewModel.
     */
    private fun observeMessageChanges() {
        viewModel.message.observe(this, Observer {
            when (it) {
                is Example2ViewModel.StateMessage.NoInternetConnection -> {
                    onShowNoInternetConnectionSnackbar()
                }
                is Example2ViewModel.StateMessage.UnauthorizedError -> {
                    onShowUnauthorizedErrorSnackbar()
                }
                is Example2ViewModel.StateMessage.UnknownError -> {
                    onShowUnknownErrorSnackbar()
                }
            }
        })
    }

    /**
     * Observes the loading state in the ViewModel and depending on that shows the SwipeRefreshLayout's
     * ProgressBar or not.
     */
    private fun observeLoadingChanges() {
        viewModel.isLoading.observe(this, Observer {
            activityExample2SwipeRefreshLayout.isRefreshing = it
        })
    }

    /**
     * Observes the state of the results in the ViewModel.
     */
    private fun observeStateChanges() {
        viewModel.state.observe(this, Observer {
            when (it) {
                is Example2ViewModel.State.ShowBusinessSkillsInformation -> {
                    onShowBusinessSkillsInformation(it.businessSkills)
                }
                is Example2ViewModel.State.ShowProfileInformation -> {
                    onShowProfileInformation(it.profile)
                }
                is Example2ViewModel.State.HideBusiness -> {
                    onHideBusinessSection()
                }
                is Example2ViewModel.State.HideProfile -> {
                    onHideProfileSection()
                }
            }
        })
    }

    private fun setupSwipeToRefreshLayout() {
        activityExample2SwipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchProfile()
        }
    }

    private fun onShowUnknownErrorSnackbar() {

    }

    private fun onShowUnauthorizedErrorSnackbar() {

    }

    private fun onShowNoInternetConnectionSnackbar() {

    }

    private fun onHideProfileSection() {

    }

    private fun onHideBusinessSection() {

    }

    private fun onShowProfileInformation(profile: ProfileInformation) {

    }

    private fun onShowBusinessSkillsInformation(businessSkills: BusinessSkills) {

    }
}