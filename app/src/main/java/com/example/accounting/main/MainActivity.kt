package com.example.accounting.main

import android.app.DatePickerDialog
import android.content.Entity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.MainListAdapter
import com.example.accounting.R
import com.example.accounting.addNewItem.AddNewItemActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        //view model
//        viewModel= ViewModelProvider(this).get(MainViewModel::class.java)
//        viewModel= MainViewModel(application)

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

        //floating button
        val fltBt: FloatingActionButton= findViewById(R.id.flt_bt_add)
        fltBt.setOnClickListener{
            val intent= Intent(this, AddNewItemActivity::class.java)
            startActivity(intent)
        }

        //activity result

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.e("test", "Get Activity Result!")

        Snackbar.make(this.findViewById(R.id.layout_main), "Result: $data", Snackbar.LENGTH_SHORT)
            .show()

        if (data != null) {
            viewModel.insertItem(data)
        }else{
            Snackbar.make(this.findViewById(R.id.layout_main), "Error", Snackbar.LENGTH_SHORT)
                .show()
        }
    }
}