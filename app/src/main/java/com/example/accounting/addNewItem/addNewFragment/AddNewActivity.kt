package com.example.accounting.addNewItem.addNewFragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.accounting.R
import com.example.accounting.addNewItem.addNewFragment.adapter.TypePagerAdapter
import com.example.accounting.database.model.ItemEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class AddNewActivity : AppCompatActivity() {

    private lateinit var pagerType: ViewPager2
    private lateinit var pagerTypeAdapter: TypePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_activity)

        val etPrice: EditText= findViewById(R.id.et_cost)
        val etName: EditText= findViewById(R.id.et_name)
        val etNote: EditText= findViewById(R.id.et_note)
        val tvToday: TextView= findViewById(R.id.tv_today)

        //view model
        val factory=
            AddNewViewModelFactory(
                application
            )
        val viewModel= ViewModelProvider(this, factory).get(AddNewViewModel::class.java)

        //view pager
        pagerType= findViewById(R.id.pager_type)
//        val viewList= listOf(R.layout.add_new_pager_type, R.layout.add_new_pager_type_2)
//        pagerTypeAdapter= AddNewViewPagerAdapter()
//        pagerType.adapter= pagerTypeAdapter
        val typePagerAdapter= TypePagerAdapter()
        pagerType.adapter= typePagerAdapter

        tvToday.text= viewModel.selectedDate.value.toString()

        //observe
        viewModel.selectedDate.observe(this, androidx.lifecycle.Observer {
            try {
                tvToday.text= viewModel.selectedDate.value.toString()
            } catch (e: Exception) {
                Snackbar.make(this.findViewById(R.id.layout_main), "$e", Snackbar.LENGTH_SHORT)
                    .show()
            }
        })


        val toolbar= findViewById<Toolbar>(R.id.toolbar_add)
        toolbar.inflateMenu(R.menu.add_new_toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item_save -> {
                    val newItem = ItemEntity(
                        0,
                        viewModel.selectedDate.value.toString(),
                        0,
                        "type",
                        etName.text.toString(),
                        etNote.text.toString(),
                        etPrice.text.toString().toInt()
                    )
                    Log.d(
                        ContentValues.TAG,
                        "Add New Item Date: " + viewModel.selectedDate.value.toString()
                    )
                    viewModel.insertItem(newItem)


                }else -> {}
            }
            finish()
            return@setOnMenuItemClickListener true
        }
    }
}
