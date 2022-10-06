//package com.example.plantshandbook.dp.repository
//
//import androidx.lifecycle.LiveData
//import com.example.plantshandbook.dp.dao.RecipeDao
//import com.example.plantshandbook.model.Recipe
//
//class RecipeRealization(private val recipeDao: RecipeDao): RecipeRepository {
//    override val allRecipe: LiveData<ArrayList<Recipe>>
//        get() = recipeDao.getAllRecipe()
//
//    override suspend fun insertRecipe(recipe: Recipe, onSuccess: () -> Unit) {
//        recipeDao.insert(recipe)
//        onSuccess()
//    }
//
//    override suspend fun deleteRecipe(recipe: Recipe, onSuccess: () -> Unit) {
//        recipeDao.delete(recipe)
//        onSuccess()
//    }
//}