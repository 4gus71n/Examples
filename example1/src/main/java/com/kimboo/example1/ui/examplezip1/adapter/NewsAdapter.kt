package com.kimboo.example1.ui.examplezip1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kimboo.example1.R
import com.kimboo.core.models.NewFeed
import kotlinx.android.synthetic.main.view_holder_example1_item.view.*
import kotlin.RuntimeException

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titleTextView: TextView = view.viewHolderExampleTitle
    val descriptionTextView: TextView = view.viewHolderExampleDescription
}

class SeparatorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titleTextView: TextView = view.viewHolderExampleTitle
}

class NewsAdapter(
    context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val news = mutableListOf<NewFeed>()
    val recentlyViewedNews = mutableListOf<NewFeed>()

    private val allNews: MutableList<NewFeed>
        get() = mutableListOf<NewFeed>().apply {
            addAll(recentlyViewedNews)
            addAll(news)
        }

    private val inflater by lazy {
        LayoutInflater.from(context)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> SEPARATOR // Recently Viewed header
            recentlyViewedNews.size + 1 -> SEPARATOR // News header
            else -> ITEM
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SEPARATOR -> {
                SeparatorViewHolder(
                    inflater.inflate(R.layout.view_holder_example1_separator, parent, false)
                )
            }
            ITEM -> {
                NewsViewHolder(
                    inflater.inflate(R.layout.view_holder_example1_item, parent, false)
                )
            }
            else -> throw RuntimeException("Unknown viewType")
        }
    }

    override fun getItemCount() = allNews.size + 2 // The + 2 is because the two headers

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SeparatorViewHolder -> {
                holder.titleTextView.text = when (position) {
                    0 -> "Recently Viewed"
                    recentlyViewedNews.size + 1 -> "News"
                    else -> throw RuntimeException("Unknown header")
                }
            }
            is NewsViewHolder -> {
                when {
                    position in (1..4) -> {
                        holder.titleTextView.text = allNews[position - 1].title
                        holder.descriptionTextView.text = allNews[position - 1].description
                    }
                    position > 4 -> {
                        holder.titleTextView.text = allNews[position - 2].title
                        holder.descriptionTextView.text = allNews[position - 2].description
                    }
                }
            }
        }
    }

    companion object {
        private const val SEPARATOR = 100
        private const val ITEM = 200
    }
}
