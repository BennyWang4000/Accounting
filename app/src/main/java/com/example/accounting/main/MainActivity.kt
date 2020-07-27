package com.example.accounting.main

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.R
import com.example.accounting.addNewItem.AddNewItemActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var viewModel: MainViewModel
    private lateinit var btPreviousDay: Button
    private lateinit var btNextDay: Button
    private lateinit var tvToday: TextView
    private lateinit var adapter:  MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        tvToday= findViewById(R.id.tv_today)
        btNextDay= findViewById(R.id.bt_next_day)
        btPreviousDay= findViewById(R.id.bt_previous_day)
        btNextDay.setOnClickListener(this)
        btPreviousDay.setOnClickListener(this)


        //floating button
        val fltBt: FloatingActionButton= findViewById(R.id.flt_bt_add)
        fltBt.setOnClickListener{
            val intent= Intent(this, AddNewItemActivity::class.java)
            startActivityForResult(intent, 1)
        }

        //view model
        val factory= MainViewModelFactory(application)
        viewModel= ViewModelProvider(this, factory).get(MainViewModel::class.java)
//        viewModel= ViewModelProvider(this).get(MainViewModel::class.java)

//        //observe data
//        viewModel.data.observe(this, Observer { item ->
//            // Update the cached copy of the words in the adapter.
//            item?.let { adapter.addNewItem(it) }
//        })

        //recycler view
         Log.e("test", viewModel.allData.toString())
        try{
            val rvList: RecyclerView= findViewById(R.id.rv_list)
//            viewModel.allData.value.let{}
            adapter = MainListAdapter(this, viewModel.allData.value!!)
            rvList.adapter = adapter
            rvList.layoutManager = LinearLayoutManager(this)
        }catch(e: Exception){
            Snackbar.make(this.findViewById(R.id.layout_main), "Recycler View Error... \n$e", Snackbar.LENGTH_SHORT)
                .show()
        }
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
                            val date = "你設定的日期為:$year ${month+ 1} $day"
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


    }

//    //activity result
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        Log.e("test", "Get Activity Result!")
//
//        Snackbar.make(this.findViewById(R.id.layout_main), "Result: ${data.toString()}", Snackbar.LENGTH_SHORT)
//            .show()
//
//        if (data != null) {
////            viewModel.insertItem(data)
//        }else{
//            Snackbar.make(this.findViewById(R.id.layout_main), "Error", Snackbar.LENGTH_SHORT)
//                .show()
//        }
//    }

    //button be clicked
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.bt_previous_day -> {
                Snackbar.make(this.findViewById(R.id.layout_main), "Previous Day...", Snackbar.LENGTH_SHORT)
                    .show()
            }
            R.id.bt_next_day -> {
                Snackbar.make(this.findViewById(R.id.layout_main), "Next Day...", Snackbar.LENGTH_SHORT)
                    .show()
            }
            else -> {
                Snackbar.make(this.findViewById(R.id.layout_main), "蛤？", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            adapter.notifyDataSetChanged()
        }catch (e: java.lang.Exception){
            Toast.makeText(this, "Notify Data Set Changed Error...\n$e", Toast.LENGTH_SHORT)
                .show()
        }
    }
}