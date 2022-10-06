package com.example.plantshandbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "recipe_table")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo
    val imageID: Int,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val category: String,
    @ColumnInfo
    val ingredients: String,
    @ColumnInfo
    val desc: String
) : Serializable
