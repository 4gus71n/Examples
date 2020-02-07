package com.kimboo.examples.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kimboo.examples.R
import kotlinx.android.synthetic.main.view_holder_example_item.view.*


class ExampleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titleTextView: TextView = view.viewHolderExampleTitle
    val descriptionTextView: TextView = view.viewHolderExampleDescription
}

class ExamplesAdapter(
    context: Context,
    var callback: Callback
) : RecyclerView.Adapter<ExampleViewHolder>() {

    interface Callback {
        fun onPositionClicked(position: Int)
    }

    private val inflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    private val examples = arrayListOf(
        Example(
            title = context.getString(R.string.example1_title),
            description = context.getString(R.string.example1_subtitle)
        ),
        Example(
            title = context.getString(R.string.example2_title),
            description = context.getString(R.string.example2_subtitle)
        ),
        Example(
            title = context.getString(R.string.cache_example1_title),
            description = context.getString(R.string.cache_example1_subtitle)
        ),
        Example(
            title = context.getString(R.string.imgur_example_title),
            description = context.getString(R.string.imgur_example_subtitle)
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        return ExampleViewHolder(
            inflater.inflate(R.layout.view_holder_example_item, parent, false)
        )
    }

    override fun getItemCount() = examples.size

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.titleTextView.text = examples[position].title
        holder.descriptionTextView.text = examples[position].description
        holder.itemView.setOnClickListener {
            callback.onPositionClicked(position)
        }
    }
}
