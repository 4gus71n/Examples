package com.kimboo.example2.ui.examplecache1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kimboo.example2.R
import com.kimboo.core.models.Recipe
import kotlinx.android.synthetic.main.view_holder_recipe.view.*

class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titleTextView: TextView = view.viewHolderExampleCacheTitle
    val descriptionTextView: TextView = view.viewHolderExampleCacheDescription
    val bookmarkImageView: ImageView = view.viewHolderExampleCacheBookmark
}

class RecipesAdapter(
    val callback: RecipesAdapter.Callback
) : RecyclerView.Adapter<RecipeViewHolder>() {

    // region Callback interface declaration
    interface Callback {
        fun onRecipeClicked(recipe: Recipe)
    }
    //endregion

    // region Variables declaration
    val recipes = mutableListOf<Recipe>()
    // endregion

    // region RecyclerView.Adapter<RecipeViewHolder> implementation
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_recipe, parent, false)
        )
    }

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        val context = holder.itemView.context

        holder.titleTextView.text = recipe.name
        holder.descriptionTextView.text = context.getString(
            R.string.ingredients_format_label,
            recipe.ingredients.joinToString(", ").plus(".")
        )

        holder.bookmarkImageView.visibility = if (recipe.isBookmarked) {
            View.VISIBLE
        } else {
            View.GONE
        }

        holder.itemView.setOnClickListener {
            callback.onRecipeClicked(recipe)
        }
    }

    // endregion
}