package com.example.accounting.main

import android.app.Application
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.accounting.R
import com.example.accounting.main.mainPager.adapter.MainPagerAdapter
import com.example.accounting.main.mainPager.adapter.MainPagerCallBack
import com.example.accounting.main.listFragment.mainFragment.MainPagerViewModel

/**
 *  找個時間製作此 app 的類別圖和流程圖
 *      - 重構各大 fragment 和 activity
 *
 *      為配合 navigation drawer 和各功能
 *  */

class MainPagerFragment() : Fragment(){

    private lateinit var viewModel: MainPagerViewModel
    private lateinit var pagerType: ViewPager2
    private lateinit var pagerAdapter: MainPagerAdapter
    private lateinit var pagerCallBack: MainPagerCallBack

    private val LIST_PAGER_MID_POSITION= Int.MAX_VALUE/ 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.main_fragment, container, false)


//        val tvToday= root.findViewById<TextView>(R.id.tv_today)
//        val btNextDay= root.findViewById<Button>(R.id.bt_next_day)
//        val btPreviousDay= root.findViewById<Button>(R.id.bt_previous_day)

//        btPreviousDay.setOnClickListener(this)


            //view model
        val factory = MainPagerViewModelFactory(activity!!.application)
        viewModel = ViewModelProvider(this, factory).get(MainPagerViewModel::class.java)
//        viewModel= ViewModelProvider(this).get(MainViewModel::class.java)
//        viewModel= ViewModelProvider(this).get(MainViewModel::class.java)


        //滑動後改變日期，並設定currentItem為中間
        //view pager
        val pagerType = root.findViewById<ViewPager2>(R.id.pager_list)
        pagerAdapter = MainPagerAdapter(viewModel, activity!!)
        pagerType.adapter = pagerAdapter
        pagerType.currentItem = LIST_PAGER_MID_POSITION
        pagerCallBack = MainPagerCallBack(viewModel, this)
        pagerType.registerOnPageChangeCallback(pagerCallBack)
        Log.d(ContentValues.TAG, "pager position: ${pagerType.currentItem}")
        // observe page be changed
//        viewModel.pagePosition.observe(this, Observer{
//            Log.d(ContentValues.TAG, "after page changed selected date: ${viewModel.selectedDate.value}")
////            pagerType.currentItem= LIST_PAGER_MID_POSITION
//            tvToday.text= viewModel.selectedDate.value.toString()
//        })



            //fragment 建立
//        changeFrg(application)

//        //button clicked
//        btNextDay.setOnClickListener {
//            viewModel.selectedDate.value= viewModel.selectedDate.value!!.plusDays(1)
//            pagerAdapter.notifyDataSetChanged()
//            pagerType.currentItem++
//        }
//        btPreviousDay.setOnClickListener {
//            viewModel.selectedDate.value= viewModel.selectedDate.value!!.plusDays(-1)
//            pagerAdapter.notifyDataSetChanged()
//            pagerType.currentItem--
//        }
        return root
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
//        Snackbar.make(this.activity!!.findViewById(R.id.layout_main),
//            "Change Date to ${viewModel.selectedDate.value}",
//            Snackbar.LENGTH_SHORT
//        ).show()
//    }
}