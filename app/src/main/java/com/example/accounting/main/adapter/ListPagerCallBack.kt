package com.example.accounting.main.adapter

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.accounting.main.MainActivity
import com.example.accounting.main.MainViewModel

/**
 *  問題：一開始所有都一樣日期，直到滑動才開始observe
 * */
class ListPagerCallBack(val viewModel: MainViewModel, val actvity: MainActivity): ViewPager2.OnPageChangeCallback() {

    private val PAGER_LIST_MID_POSITION= Int.MAX_VALUE/ 2
    private var lastPosition= PAGER_LIST_MID_POSITION

    init{
        viewModel.selectedDate.observe(actvity, Observer {
            Log.d(ContentValues.TAG, "selected dated: ${viewModel.selectedDate.value}")
        })
    }

    override fun onPageScrollStateChanged(state: Int) {
        super.onPageScrollStateChanged(state)
//        Log.d(ContentValues.TAG, "onPageScrollStateChanged()")
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
//        Log.d(ContentValues.TAG, "onPageScrolled()")
    }

    override fun onPageSelected(position: Int){
        super.onPageSelected(position)
        if(viewModel.lastPosition.value!!> position){
            viewModel.pagePosition.let {
                it.value= it.value!!- 1
            }
            viewModel.selectedDate.value= viewModel.selectedDate.value!!.plusDays(-1)
            Log.d(ContentValues.TAG, "Scrolled Left  position: ${viewModel.pagePosition.value}")
        }else if(viewModel.lastPosition.value!!< position){
            viewModel.pagePosition.let {
                it.value= it.value!!+ 1
            }
            viewModel.selectedDate.value= viewModel.selectedDate.value!!.plusDays(1)
            Log.d(ContentValues.TAG, "Scrolled right  position: ${viewModel.pagePosition.value}")
        }
        viewModel.lastPosition.value= position
    }
}