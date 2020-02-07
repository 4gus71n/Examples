package com.kimboo.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.kimboo.core.R

class SimpleStateDisplay(
    context: Context,
    attributeSet: AttributeSet
) : FrameLayout(context, attributeSet) {

    private val simpleStateViewTitle by lazy {
        findViewById<TextView>(R.id.simpleStateViewTitle)
    }

    private val simpleStateViewImage by lazy {
        findViewById<ImageView>(R.id.simpleStateViewImage)
    }

    fun hide() {
        visibility = View.GONE
    }

    fun show(callback: SimpleStateDisplay.() -> Unit) {
        callback()
        visibility = View.VISIBLE
    }

    fun title(@StringRes stringRes: Int) {
        simpleStateViewTitle.text = context.getString(stringRes)
    }

    fun title(string: String) {
        simpleStateViewTitle.text = string
    }

    fun image(@DrawableRes drawableRes: Int) {
        simpleStateViewImage.setImageResource(drawableRes)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_simple_state_view, this, true)
        visibility = View.GONE
    }
}