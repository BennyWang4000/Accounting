package com.example.accounting.main.mainPager.adapter

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.accounting.main.MainPagerFragment
import com.example.accounting.main.listFragment.mainFragment.MainPagerViewModel

/**
 *  問題：一開始所有都一樣日期，直到滑動才開始observe
 * */
class MainPagerCallBack(val viewModel: MainPagerViewModel, val actvity: MainPagerFragment): ViewPager2.OnPageChangeCallback() {

    private val _pagerListMidPosition= Int.MAX_VALUE/ 2
    private var lastPosition= _pagerListMidPosition

    init{
        viewModel.selectedDate.observe(actvity, Observer {
            Log.d(ContentValues.TAG, "selected dated: ${viewModel.selectedDate.value}")
        })
    }

    override fun onPageSelected(position: Int){
        super.onPageSelected(position)
        if(viewModel.lastPosition.value!!> position){

//            viewModel.currentPosition.let {
//                it.value= it.value!!- 1
//            }

            viewModel.selectedDate.value= viewModel.selectedDate.value!!.plusDays(-1)
            Log.d(ContentValues.TAG, "Scrolled Left  position: ${viewModel.pagePosition.value}")
        }else if(viewModel.lastPosition.value!!< position){

//            viewModel.currentPosition.let {
//                it.value= it.value!!+ 1
//            }

            viewModel.selectedDate.value= viewModel.selectedDate.value!!.plusDays(1)
            Log.d(ContentValues.TAG, "Scrolled right  position: ${viewModel.pagePosition.value}")
        }
        viewModel.lastPosition.value= position
        viewModel.pagePosition.value= position
    }
}