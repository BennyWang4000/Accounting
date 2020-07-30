package com.example.accounting.addNewItem

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.example.accounting.R
import com.example.accounting.room.ItemEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.util.*

class AddNewActivity : AppCompatActivity() {

    private lateinit var pagerType: ViewPager2
    private lateinit var pagerAdapter: AddNewViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_activity)
        val etPrice: EditText= findViewById(R.id.et_cost)
        val etName: EditText= findViewById(R.id.et_name)
        val etNote: EditText= findViewById(R.id.et_note)
        val btAdd: FloatingActionButton = findViewById(R.id.flt_bt_save)
        val tvToday: TextView= findViewById(R.id.tv_today)

        //view model
        val factory= AddNewViewModelFactory(application)
        val viewModel= ViewModelProvider(this, factory).get(AddNewViewModel::class.java)

        //view pager
        val viewList= listOf(R.layout.add_new_pager_type_1, R.layout.add_new_pager_type_2)
        pagerAdapter= AddNewViewPagerAdapter()

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

        //button is clicked
        btAdd.setOnClickListener{
            var newItem= ItemEntity(
                0,
                viewModel.selectedDate.value.toString(),
                0,
                "type",
                etName.text.toString(),
                etNote.text.toString(),
                etPrice.text.toString().toInt()
            )
            Log.d(ContentValues.TAG, "Add New Item Date: "+ viewModel.selectedDate.value.toString())

            viewModel.insertItem(newItem)
            finish()
        }
    }
}
