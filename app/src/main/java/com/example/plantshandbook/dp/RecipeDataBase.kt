package com.example.plantshandbook.dp

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.plantshandbook.dp.dao.RecipeDao
import com.example.plantshandbook.model.Recipe


@Database(entities = [Recipe::class], version = 1)
abstract class RecipeDataBase: RoomDatabase() {
    abstract fun getDao(): RecipeDao

    companion object{
        fun getDb(context: Context): RecipeDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                RecipeDataBase::class.java,
                "RecipeBook.db"
            ).build()
        }
    }
}