package com.example.plantshandbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import com.example.plantshandbook.adapter.RecipeAdapter
import com.example.plantshandbook.databinding.ActivityContentBinding
import com.example.plantshandbook.dp.RecipeDataBase
import com.example.plantshandbook.model.Recipe

class ContentActivity : AppCompatActivity() {

    lateinit var binding: ActivityContentBinding
    private var editLauncherContent: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = RecipeDataBase.getDb(this)
        val item = intent.getSerializableExtra("item") as Recipe
        binding.apply {
            tbContent.inflateMenu(R.menu.content_menu)
            tbContent.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.change -> onChangeRecipe()
                    R.id.delete -> {
                        Thread {
                            db.getDao().delete(item)
                        }.start()
                        finish()
                    }
                    R.id.back -> finish()
                }
                true
            }
            imMain.setImageResource(item.imageID)
            tvTitle.text = item.title
            tvCategory.text = item.category
            tvIngr.text = item.ingredients
            tvDesc.text = item.desc
        }
    }

    private fun onChangeRecipe() {
//        editLauncherContent =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//
//            }
//
//        editLauncherContent?.launch(Intent(this@ContentActivity, EditActivity::class.java).apply {
//            putExtra(Constant.TOEDIT, Constant.RESULT_BACK)
//           putExtra("itemEdit", item)
//        })
//
    }

}