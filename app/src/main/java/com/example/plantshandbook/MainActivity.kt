package com.example.plantshandbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantshandbook.adapter.RecipeAdapter
import com.example.plantshandbook.databinding.ActivityMainBinding
import com.example.plantshandbook.dp.RecipeDataBase
import com.example.plantshandbook.model.Recipe

class MainActivity : AppCompatActivity(), RecipeAdapter.Listener {

    lateinit var binding: ActivityMainBinding
    private val adapter = RecipeAdapter(this)
    private var editLauncherMain: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }

    private fun init() {
        val db = RecipeDataBase.getDb(this)
        db.getDao().getAllRecipe().asLiveData().observe(this) {
            adapter.clearAll()
            it.forEach { recipe ->
                adapter.addRecipe(recipe)
            }
        }
        binding.apply {
            tbMain.inflateMenu(R.menu.main_menu)
            tbMain.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.filter -> onItemListenerFilter()
                    R.id.search -> onItemListenerSearch()
                }
                true
            }
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter = adapter
            fabAdd.setOnClickListener() {
                startActivity(Intent(
                    this@MainActivity, EditActivity::class.java
                ).apply {
                    putExtra(Constant.TOEDIT, Constant.RESULT_OK)
                })
            }
        }
//        editLauncherMain =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultCode ->
//                if (resultCode.resultCode == RESULT_OK) {
//
//                }
//            }

    }

    override fun onClick(recipe: Recipe) {
        startActivity(Intent(this, ContentActivity::class.java).apply {
            putExtra("item", recipe)
        })
    }

    private fun onItemListenerFilter() {
        //Log.d("mylog", "Фильтр")
    }

    private fun onItemListenerSearch() {
        Log.d("mylog", "Серч")
    }

}



