package com.example.accounting.main

import android.app.DatePickerDialog
import android.content.ContentValues
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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.accounting.R
import com.example.accounting.addNewItem.AddNewActivity
import com.example.accounting.main.adapter.ListPagerAdapter
import com.example.accounting.main.adapter.ListPagerCallBack
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate

/**
 *  找個時間製作此 app 的類別圖和流程圖
 *      - 重構各大 fragment 和 activity
 *
 *      為配合 navigation drawer 和各功能
 *  */

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var viewModel: MainViewModel
    private lateinit var btPreviousDay: Button
    private lateinit var btNextDay: Button
    private lateinit var tvToday: TextView
    private lateinit var pagerType: ViewPager2
    private lateinit var pagerAdapter: ListPagerAdapter
    private lateinit var pagerCallBack: ListPagerCallBack

    private val LIST_PAGER_MID_POSITION= Int.MAX_VALUE/ 2

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
//        viewModel= ViewModelProvider(this).get(MainViewModel::class.java)



        //滑動後改變日期，並設定currentItem為中間
        //view pager
        pagerType= findViewById(R.id.pager_list)
        pagerAdapter= ListPagerAdapter(application, viewModel, this)
        pagerType.adapter= pagerAdapter
        pagerType.currentItem= LIST_PAGER_MID_POSITION

        pagerCallBack= ListPagerCallBack(viewModel, this)
        pagerType.registerOnPageChangeCallback(pagerCallBack)
        Log.d(ContentValues.TAG, "pager position: ${pagerType.currentItem}")

        // observe page be changed
//        viewModel.pagePosition.observe(this, Observer{
//            Log.d(ContentValues.TAG, "after page changed selected date: ${viewModel.selectedDate.value}")
////            pagerType.currentItem= LIST_PAGER_MID_POSITION
//            tvToday.text= viewModel.selectedDate.value.toString()
//        })

        //observe selected date
        viewModel.selectedDate.observe(this, Observer { date ->
            // Update the cached copy of the words in the adapter.
            date?.let {
                Log.d(ContentValues.TAG, "Main View Observe Date: $date")
                tvToday.text= viewModel.selectedDate.value.toString()
            }
        })
        //fragment 建立
//        changeFrg(application)


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
                            viewModel.selectedDate.value= LocalDate.parse(
                                "$year-" +
                                        String.format("%02d", month+ 1)+ "-" +
                                        String.format("%02d", day))

//                            changeFrg(application)
                            pagerAdapter.notifyDataSetChanged()
                        }
                    }, viewModel.selectedDate.value!!.year
                        , String.format("%02d", viewModel.selectedDate.value!!.monthValue.plus(-1)).toInt()
                        , String.format("%02d", viewModel.selectedDate.value!!.dayOfMonth).toInt())
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
                viewModel.selectedDate.value= viewModel.selectedDate.value!!.plusDays(-1)
                pagerAdapter.notifyDataSetChanged()
                pagerType.currentItem--
//                changeFrg(application)
            }
            R.id.bt_next_day -> {
                viewModel.selectedDate.value= viewModel.selectedDate.value!!.plusDays(1)
                pagerAdapter.notifyDataSetChanged()
                pagerType.currentItem++
//                changeFrg(application)
            }
            else -> {
                Snackbar.make(this.findViewById(R.id.layout_main), "Unknown Button...", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }
    /**
     * 疑問：fragment 是在 activity 底下的物件，那透過 activity 改變 fragment 是否違規？
     * */
//    //change fragment
//    private fun changeFrg(application: Application){
//        var frg= ListFragment(application)
//        val transaction: FragmentTransaction= supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.frg_list, frg)
//        transaction.commit()
//
//        Snackbar.make(this.findViewById(R.id.layout_main),
//            "Change Date to ${viewModel.selectedDate.value}",
//            Snackbar.LENGTH_SHORT
//        ).show()
//    }
}