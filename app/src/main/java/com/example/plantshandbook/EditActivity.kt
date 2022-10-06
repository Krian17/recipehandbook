package com.example.plantshandbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.plantshandbook.databinding.ActivityEditBinding
import com.example.plantshandbook.dp.RecipeDataBase
import com.example.plantshandbook.model.Recipe

class EditActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var binding: ActivityEditBinding

    private var indexImage = 0
    private var imageId = R.drawable.image1

    private val imageIdList = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5
    )

    private val categoryList = listOf("Гарнир", "Мясо", "Десерт", "Закуски", "Салаты")
    var choseCategory: String = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when (intent.getIntExtra(Constant.TOEDIT, Constant.RESULT_OK)) {
            Constant.RESULT_OK -> {
                Log.d("mylog", intent.getIntExtra(Constant.TOEDIT, 0).toString())
                initButtons()
                initSpinner()
            }
            Constant.RESULT_BACK -> {
                Log.d("mylog", intent.getIntExtra(Constant.TOEDIT, 0).toString())
//                val recipeIntent = intent.getSerializableExtra("itemEdit") as Recipe
//                initButtons()
////              initSpinner()
                //    binding.apply {
//                    edTitle.setText(recipeIntent.title)
//                    edingred.setText(recipeIntent.ingredients)
//                    edDesc.setText(recipeIntent.desc)
//                }
            }
        }
    }

    private fun initButtons() {
        val db = RecipeDataBase.getDb(this)
        binding.apply {
            btnNext.setOnClickListener {
                indexImage++
                if (indexImage > imageIdList.size - 1) indexImage = 0
                imageId = imageIdList[indexImage]
                imageView.setImageResource(imageId)

            }
            fabDone.setOnClickListener {
                val recipe = Recipe(
                    null,
                    imageId,
                    edTitle.text.toString(),
                    choseCategory,
                    edingred.text.toString(),
                    edDesc.text.toString()
                )

                Thread{
                    db.getDao().insert(recipe)
                }.start()

                setResult(RESULT_OK)
                finish()
            }
        }
    }

    private fun initSpinner() = with(binding) {
        spinner.adapter = ArrayAdapter(
            this@EditActivity,
            R.layout.support_simple_spinner_dropdown_item,
            categoryList
        )
        spinner.onItemSelectedListener = this@EditActivity
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        choseCategory = categoryList[p2]
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(this, "Категория не выбрана", Toast.LENGTH_LONG).show()
    }
}