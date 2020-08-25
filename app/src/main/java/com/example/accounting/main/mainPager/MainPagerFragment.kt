package com.example.accounting.main

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.accounting.R
import com.example.accounting.budget.BudgetActivity
import com.example.accounting.main.mainPager.adapter.MainPagerAdapter
import com.example.accounting.main.mainPager.adapter.MainPagerCallBack
import com.example.accounting.main.listFragment.mainFragment.MainPagerViewModel

/**
 *  找個時間製作此 app 的類別圖和流程圖
 *      - 重構各大 fragment 和 activity
 *
 *      為配合 navigation drawer 和各功能
 *  */

class MainPagerFragment() : Fragment(), View.OnClickListener{

    private lateinit var viewModel: MainPagerViewModel
    private lateinit var pagerType: ViewPager2
    private lateinit var pagerAdapter: MainPagerAdapter
    private lateinit var pagerCallBack: MainPagerCallBack
    private lateinit var layoutBudget: ConstraintLayout

    private val LIST_PAGER_MID_POSITION= Int.MAX_VALUE/ 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.main_fragment, container, false)
            //view model
        val factory = MainPagerViewModelFactory(activity!!.application)
        viewModel = ViewModelProvider(this, factory).get(MainPagerViewModel::class.java)


//        //budget
        val tvTotalBudget= root.findViewById<TextView>(R.id.main_tv_total_budget)
        val tvRemainingBudget= root.findViewById<TextView>(R.id.main_tv_remaining_budget)
        layoutBudget= root.findViewById(R.id.main_layout_budget)
        layoutBudget.setOnClickListener(this)

        viewModel.settings.observe(this, Observer {
            viewModel.totalBudgetAmount.value= it[0].budgetAmount.toDouble()
            tvTotalBudget.text= viewModel.totalBudgetAmount.value.toString()

            if(viewModel.settings.value!![0].budgetShow){
                layoutBudget.visibility= View.VISIBLE
            } else{
                layoutBudget.visibility= View.GONE
            }
        })

        viewModel.selectedDate.observe(this, Observer {
            // month value 1 = January
            if (viewModel.selectedMonth.value!= it.monthValue) {
                viewModel.selectedMonth.value = it.monthValue
            }
        })

        viewModel.selectedMonth.observe(this, Observer{
            Log.e("TEST", "month is changed")
            tvRemainingBudget.text= (viewModel.totalBudgetAmount.value!!- viewModel.getMonthSum(
                "2020$it"
            )).toString()
        })


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
        return root
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.main_layout_budget -> {
                startActivity(Intent(activity, BudgetActivity::class.java))
            }
            else -> {

            }
        }
    }

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