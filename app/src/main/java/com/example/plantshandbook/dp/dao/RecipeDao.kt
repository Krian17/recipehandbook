package com.example.plantshandbook.dp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plantshandbook.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(recipe: Recipe)

    @Delete
    fun delete(recipe: Recipe)

    @Query("SELECT * from recipe_table")
    fun getAllRecipe(): Flow<List<Recipe>>

}