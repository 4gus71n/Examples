package com.kimboo.example1.ui.examplezip2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.appbar.AppBarLayout
import com.kimboo.example1.R
import com.kimboo.example1.di.component.DaggerExample1ViewInjector
import com.kimboo.example1.di.component.Example1ViewInjector
import com.kimboo.example1.ui.examplezip2.viewmodel.ExampleZip2ViewModel
import com.kimboo.models.BusinessSkills
import com.kimboo.models.ProfileInformation
import com.kimboo.utils.MyViewModelFactory
import com.kimboo.utils.getBaseSubComponent
import kotlinx.android.synthetic.main.activity_example_2.*
import kotlinx.android.synthetic.main.activity_example_2_business.*
import kotlinx.android.synthetic.main.activity_example_2_profile.*
import javax.inject.Inject
import kotlin.math.abs

class ExampleZip2Activity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: MyViewModelFactory
    lateinit var viewModelZip: ExampleZip2ViewModel

    private val viewInjector: Example1ViewInjector
         get() = DaggerExample1ViewInjector.builder()
             .baseSubComponent(getBaseSubComponent())
             .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_2)

        setupSwipeToRefreshLayout()
        setupParallaxFadeOut()
        setupToolbar()

        viewInjector.inject(this)

        viewModelZip = ViewModelProviders.of(this, viewModelProvider)
            .get(ExampleZip2ViewModel::class.java)

        observeStateChanges()
        observeLoadingChanges()
        observeMessageChanges()

        viewModelZip.fetchProfile()
    }

    private fun setupToolbar() {
        setSupportActionBar(activityExample2Toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupParallaxFadeOut() {
        activityExample2AppbarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            activityExample2DescriptionContainer.alpha = 1 - abs(
                verticalOffset.toFloat() / appBarLayout.totalScrollRange
            )
        })
    }

    /**
     * Observes the message changes from the ViewModel.
     */
    private fun observeMessageChanges() {
        viewModelZip.message.observe(this, Observer {
            when (it) {
                is ExampleZip2ViewModel.StateMessage.NoInternetConnection -> {
                    onShowNoInternetConnectionSnackbar()
                }
                is ExampleZip2ViewModel.StateMessage.UnauthorizedError -> {
                    onShowUnauthorizedErrorSnackbar()
                }
                is ExampleZip2ViewModel.StateMessage.UnknownError -> {
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
        viewModelZip.isLoading.observe(this, Observer {
            activityExample2SwipeRefreshLayout.isRefreshing = it
        })
    }

    /**
     * Observes the state of the results in the ViewModel.
     */
    private fun observeStateChanges() {
        viewModelZip.state.observe(this, Observer {
            when (it) {
                is ExampleZip2ViewModel.State.ShowBusinessSkillsInformation -> {
                    onShowBusinessSkillsInformation(it.businessSkills)
                }
                is ExampleZip2ViewModel.State.ShowProfileInformation -> {
                    onShowProfileInformation(it.profile)
                }
                is ExampleZip2ViewModel.State.HideBusiness -> {
                    onHideBusinessSection()
                }
                is ExampleZip2ViewModel.State.HideProfile -> {
                    onHideProfileSection()
                }
            }
        })
    }

    private fun setupSwipeToRefreshLayout() {
        activityExample2SwipeRefreshLayout.setOnRefreshListener {
            viewModelZip.fetchProfile()
        }
    }

    private fun onShowUnknownErrorSnackbar() {

    }

    private fun onShowUnauthorizedErrorSnackbar() {

    }

    private fun onShowNoInternetConnectionSnackbar() {

    }

    private fun onHideProfileSection() {
        activityExample2ProfileContainer.visibility = View.GONE
    }

    private fun onHideBusinessSection() {
        activityExample2BusinessContainer.visibility = View.GONE
    }

    private fun onShowProfileInformation(profile: ProfileInformation) {
        activityExample2ProfileContainer.visibility = View.VISIBLE
        activityExample2FullNameTextView.text = "${profile.firsName} ${profile.lastName}"
        activityExample2EmailTextView.text = profile.email
    }

    private fun onShowBusinessSkillsInformation(businessSkills: BusinessSkills) {
        activityExample2BusinessContainer.visibility = View.VISIBLE
        activityExample2CareerTextView.text = businessSkills.career
        activityExample2YearsTextView.text = businessSkills.yearsExperience.toString()
        activityExample2LanguagesTextView.text = businessSkills.languages.joinToString(
            separator = ", "
        )
    }
}