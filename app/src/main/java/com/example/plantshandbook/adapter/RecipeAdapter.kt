package com.example.plantshandbook.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plantshandbook.MainActivity
import com.example.plantshandbook.R
import com.example.plantshandbook.databinding.PlantItemBinding
import com.example.plantshandbook.model.Recipe

class RecipeAdapter(private val listener: MainActivity) : RecyclerView.Adapter<RecipeAdapter.RecipeHolder>() {

    val recipeList = ArrayList<Recipe>()

    class RecipeHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = PlantItemBinding.bind(item)
        fun bind(recipe: Recipe, listener: Listener) = with(binding) {
            im.setImageResource(recipe.imageID)
            tvTitle.text = recipe.title
            tvCategory.text = recipe.category
            itemView.setOnClickListener {
                listener.onClick(recipe)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plant_item, parent, false)
        return RecipeHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.bind(recipeList[position], listener)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addRecipe(recipe: Recipe) {
        recipeList.add(recipe)
        notifyDataSetChanged()
    }

    fun clearAll() {
        recipeList.clear()
    }

    interface Listener {
        fun onClick(recipe: Recipe)
    }
}