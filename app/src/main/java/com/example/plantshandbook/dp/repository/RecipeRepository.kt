package com.example.plantshandbook.dp.repository

import androidx.lifecycle.LiveData
import com.example.plantshandbook.model.Recipe

interface RecipeRepository {
    val allRecipe: LiveData<ArrayList<Recipe>>

    suspend fun insertRecipe(recipe: Recipe, onSuccess:() -> Unit)
    suspend fun deleteRecipe(recipe: Recipe, onSuccess:() -> Unit)
}