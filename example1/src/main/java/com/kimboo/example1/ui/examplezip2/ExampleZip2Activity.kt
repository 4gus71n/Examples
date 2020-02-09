package com.kimboo.example1.ui.examplezip2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.kimboo.example1.R
import com.kimboo.example1.di.component.DaggerExample1ViewInjector
import com.kimboo.example1.di.component.Example1ViewInjector
import com.kimboo.example1.ui.examplezip2.viewmodel.ExampleZip2ViewModel
import com.kimboo.core.models.BusinessSkills
import com.kimboo.core.models.ProfileInformation
import com.kimboo.core.utils.MyViewModelFactory
import com.kimboo.core.utils.getBaseSubComponent
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
                is ExampleZip2ViewModel.Message.NoInternetConnection -> {
                    onShowNoInternetConnectionSnackbar()
                }
                is ExampleZip2ViewModel.Message.UnknownError -> {
                    onShowUnknownErrorSnackbar()
                }
            }
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
                is ExampleZip2ViewModel.State.IsLoading -> {
                    onChangeLoadingState(it.loading)
                }
                is ExampleZip2ViewModel.State.HideBusiness -> {
                    onHideBusinessSection()
                }
                is ExampleZip2ViewModel.State.HideProfile -> {
                    onHideProfileSection()
                }
                is ExampleZip2ViewModel.State.UnknownError -> {
                    onShownUnknownErrorStateView()
                }
                is ExampleZip2ViewModel.State.NoInternetConnection -> {
                    onShowNoInternetConnectionStateView()
                }
            }
        })
    }

    private fun onShownUnknownErrorStateView() {
        activityExample2StateDisplay.show {
            image(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            title(R.string.error_unknown_error)
        }
    }

    private fun onShowNoInternetConnectionStateView() {
        activityExample2StateDisplay.show {
            image(R.drawable.ic_signal_wifi_off_black_24dp)
            title(R.string.error_no_internet_connection)
        }
    }

    private fun onChangeLoadingState(loading: Boolean) {
        activityExample2SwipeRefreshLayout.isRefreshing = loading
    }

    private fun setupSwipeToRefreshLayout() {
        activityExample2SwipeRefreshLayout.setOnRefreshListener {
            viewModelZip.fetchProfile()
        }
    }

    private fun onShowUnknownErrorSnackbar() {
        Snackbar.make(
            activityExample2Container,
            getString(R.string.error_unknown_error),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun onShowNoInternetConnectionSnackbar() {
        Snackbar.make(
            activityExample2Container,
            getString(R.string.error_no_internet_connection),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun onHideProfileSection() {
        activityExample2ProfileContainer.visibility = View.GONE
        Snackbar.make(
            activityExample2Container,
            getString(R.string.error_fetching_profile_info),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun onHideBusinessSection() {
        activityExample2BusinessContainer.visibility = View.GONE
        Snackbar.make(
            activityExample2Container,
            getString(R.string.error_fetching_business_skills),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun onShowProfileInformation(profile: ProfileInformation) {
        activityExample2StateDisplay.hide()
        activityExample2ProfileContainer.visibility = View.VISIBLE
        activityExample2FullNameTextView.text = "${profile.firsName} ${profile.lastName}"
        activityExample2EmailTextView.text = profile.email
    }

    private fun onShowBusinessSkillsInformation(businessSkills: BusinessSkills) {
        activityExample2StateDisplay.hide()
        activityExample2BusinessContainer.visibility = View.VISIBLE
        activityExample2CareerTextView.text = businessSkills.career
        activityExample2YearsTextView.text = businessSkills.yearsExperience.toString()
        activityExample2LanguagesTextView.text = businessSkills.languages.joinToString(
            separator = ", "
        )
    }
}