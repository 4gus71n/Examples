package com.kimboo.examples.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.kimboo.core.activities.ActivityBuilder
import com.kimboo.examples.R
import com.kimboo.examples.ui.adapter.ExamplesAdapter
import kotlinx.android.synthetic.main.main_activity.*

/**
 * Displays a list to link to all the examples.
 */
class MainActivity : AppCompatActivity(), ExamplesAdapter.Callback {

    // region ExamplesAdapter.Callback implementation
    override fun onPositionClicked(position: Int) {
        when (position) {
            0 -> {
                startActivity(ActivityBuilder.getExample1Intent(this))
            }
        }
    }
    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        mainActivityExamplesRecyclerView.adapter = ExamplesAdapter(this, this)
    }
}