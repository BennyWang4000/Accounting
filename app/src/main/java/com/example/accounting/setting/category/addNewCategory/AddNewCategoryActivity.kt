package com.example.accounting.setting.category.addNewCategory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.accounting.R
import com.example.accounting.database.model.CategoryEntity

class AddNewCategoryActivity : AppCompatActivity() {
    private lateinit var viewModel: AddNewCategoryViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_add_new_activity)

        val tvAddNew= findViewById<TextView>(R.id.category_add_new_et)

        val behavior= intent.extras!!.getInt("behavior")
        Log.v("behavior", "$behavior")

        //view model
        val factory= AddNewCategoryViewModelFactory(application, behavior)
        viewModel= ViewModelProvider(this, factory).get(AddNewCategoryViewModel::class.java)

        //tool bar
        val toolbar= findViewById<Toolbar>(R.id.toolbar_edit)
        toolbar.overflowIcon = AppCompatResources.getDrawable(
            application.applicationContext,
            R.drawable.ic_baseline_more_vert_24_white
        )
        toolbar.inflateMenu(R.menu.save_tool_bar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item_save -> {
                    val newCategory = CategoryEntity(
                        0,
                        tvAddNew.text.toString(),
                        "",
                        0,
                        "",
                        behavior
                    )
                    viewModel.insertCategory(newCategory)
                }else -> {}
            }
            finish()
            return@setOnMenuItemClickListener true
        }
    }
}