package com.kimboo.example3.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kimboo.core.models.imgur.ImgurGallery
import com.kimboo.example3.R
import com.squareup.picasso.Picasso

class ImgurViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val image: ImageView = view.findViewById(R.id.viewHolderImgurPostImage)
    val title: TextView = view.findViewById(R.id.viewHolderImgurPostTitle)
}

class ImgurAdapter(
    private val layoutInflater: LayoutInflater
) : RecyclerView.Adapter<ImgurViewHolder>() {

    // region Variables declaration
    val imgurPosts = mutableListOf<ImgurGallery>()
    private val diffCallback = ImgurDiffUtilCallback(emptyList(), emptyList())
    // endregion

    // region RecyclerView.Adapter<ImgurViewHolder> implementation
    fun getDiffUtilsCallback(newImgurList: List<ImgurGallery>) : DiffUtil.Callback {
        return diffCallback.apply {
            newList = newImgurList
            oldList = imgurPosts
        }
    }

    override fun getItemCount() = imgurPosts.size

    override fun onBindViewHolder(holder: ImgurViewHolder, position: Int) {
        val imgurGallery = imgurPosts[position]
        holder.title.text = imgurGallery.title
        Picasso.get().load(imgurGallery.mediumCover).into(holder.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgurViewHolder {
        return ImgurViewHolder(
            layoutInflater.inflate(R.layout.view_holder_imgur_post, parent, false)
        )
    }

    private class ImgurDiffUtilCallback(
        var oldList: List<ImgurGallery>,
        var newList: List<ImgurGallery>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList.getOrNull(oldItemPosition) == newList.getOrNull(newItemPosition)
        }
        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList.getOrNull(oldItemPosition) == newList.getOrNull(newItemPosition)
        }
    }
    // endregion
}