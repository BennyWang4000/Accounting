package com.example.accounting.main

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.MainListAdapter
import com.example.accounting.R
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        //view model
        val viewModel= MainViewModel()
        //tool bar
        val toolbar= findViewById<Toolbar>(R.id.toolbar_main)
        toolbar.overflowIcon = getDrawable(R.drawable.ic_baseline_more_vert_24_white)
        toolbar.inflateMenu(R.menu.main_setting)
        toolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.item_setting -> {
                    Snackbar.make(this.findViewById(R.id.layout_main), "setting...", Snackbar.LENGTH_SHORT)
                        .show()
                    return@setOnMenuItemClickListener true
                }
                R.id.item_info -> {
                    Snackbar.make(this.findViewById(R.id.layout_main), "app info...", Snackbar.LENGTH_SHORT)
                        .show()
                    return@setOnMenuItemClickListener true
                }
                R.id.item_date -> {
                    DatePickerDialog(this, { _, year, month, day ->
                        run {
                            val date = "你設定的日期為:$year $month $day"
                            Snackbar.make(this.findViewById(R.id.layout_main), date, Snackbar.LENGTH_SHORT)
                                .show()
                        }
                    }, viewModel.getCurrentDate()[0], viewModel.getCurrentDate()[1], viewModel.getCurrentDate()[2])
                        .show()
                return@setOnMenuItemClickListener true
                }

                else -> {
                    Snackbar.make(this.findViewById(R.id.layout_main), "Unknown Issue", Snackbar.LENGTH_SHORT)
                        .show()
                    return@setOnMenuItemClickListener true
                }
            }
        }

        //navigation drawer

        //recycler view
        val rvList: RecyclerView= findViewById(R.id.rv_list)
        val adapter = MainListAdapter(this)
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
    }
}