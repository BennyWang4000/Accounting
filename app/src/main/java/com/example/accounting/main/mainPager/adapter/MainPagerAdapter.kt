package com.example.accounting.main.mainPager.adapter

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.accounting.main.listFragment.ListFragment
import com.example.accounting.main.listFragment.mainFragment.MainPagerViewModel

/**
 * 將 listFragment 的東西移動到此 adapter 中
 *      施工完成後預計下一個做 item info
 *
 * 目前想到的問題；
 *      - 個數：return item count?
 * */
class MainPagerAdapter(val viewModel: MainPagerViewModel, activity: FragmentActivity) : FragmentStateAdapter(activity){

    private val PAGER_LIST_MAX_VALUE= Int.MAX_VALUE
    private val PAGER_LIST_MID_POSITION= Int.MAX_VALUE/ 2
    private val currentDate= viewModel.currentDate.value
    private val selectedDate= viewModel.selectedDate

    override fun getItemCount(): Int = PAGER_LIST_MAX_VALUE

    override fun createFragment(position: Int): Fragment {
        return ListFragment(position)
    }
}