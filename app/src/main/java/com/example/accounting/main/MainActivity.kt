package com.example.accounting.main

import android.app.Application
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.accounting.R
import com.example.accounting.addNewItem.AddNewActivity
import com.example.accounting.main.listFragment.ListFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var viewModel: MainViewModel
    private lateinit var btPreviousDay: Button
    private lateinit var btNextDay: Button
    private lateinit var tvToday: TextView
    private lateinit var frgList: Fragment
    private val transaction: FragmentTransaction= supportFragmentManager.beginTransaction()

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        tvToday= findViewById(R.id.tv_today)
        btNextDay= findViewById(R.id.bt_next_day)
        btPreviousDay= findViewById(R.id.bt_previous_day)
        btNextDay.setOnClickListener(this)
        btPreviousDay.setOnClickListener(this)


        //view model
        val factory= MainViewModelFactory(application)
        viewModel= ViewModelProvider(this, factory).get(MainViewModel::class.java)
//        viewModel= ViewModelProvider(this).get(MainViewModel::class.java)


        //observe
        viewModel.selectedDate.observe(this, Observer { date ->
            // Update the cached copy of the words in the adapter.
            date?.let {
                Log.d("test", "Selected Date Observe")
                tvToday.text= viewModel.selectedDate.value.toString()
            }
        })

        //fragment 建立
        transaction.replace(R.id.frg_list, ListFragment(application))
        transaction.commit()


        //floating button
        val fltBt: FloatingActionButton= findViewById(R.id.flt_bt_add)
        fltBt.setOnClickListener{
            val intent= Intent(this, AddNewActivity::class.java)
            startActivityForResult(intent, 1)
        }


        //tool bar
        val toolbar= findViewById<Toolbar>(R.id.toolbar_main)
        toolbar.overflowIcon = getDrawable(R.drawable.ic_baseline_more_vert_24_white)
        toolbar.inflateMenu(R.menu.main_toolbar)
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

                            viewModel.selectedDate.value= LocalDate.parse("$year$month$day")

                        }
                    }, viewModel.selectedDate.value!!.year
                        , viewModel.selectedDate.value!!.monthValue
                        , viewModel.selectedDate.value!!.dayOfMonth)
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

    //button be clicked
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.bt_previous_day -> {
                Snackbar.make(this.findViewById(R.id.layout_main), "Previous Day...", Snackbar.LENGTH_SHORT)
                    .show()

                viewModel.selectedDate.value= viewModel.selectedDate.value!!.plusDays(-1)

                Log.d("test", viewModel.selectedDate.value.toString())
            }
            R.id.bt_next_day -> {
                Snackbar.make(this.findViewById(R.id.layout_main), "Next Day...", Snackbar.LENGTH_SHORT)
                    .show()

                viewModel.selectedDate.value= viewModel.selectedDate.value!!.plusDays(1)

                Log.d("test", viewModel.selectedDate.value.toString())
            }
            else -> {
                Snackbar.make(this.findViewById(R.id.layout_main), "蛤？", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }
    /**
     * 疑問：fragment 是在 activity 底下的物件，那透過 activity 改變 fragment 是否違規？
     * */
    //change fragment
    private fun changeFrg(date: List<Int>, application: Application){
        val frg= ListFragment(application)
        transaction.replace(R.id.frg_list, frg)
        transaction.commit()
    }
}