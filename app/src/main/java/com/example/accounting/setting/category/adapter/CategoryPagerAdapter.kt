package com.example.accounting.setting.category.adapter.CategoryPagerAdapter

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.accounting.setting.category.categoryFragment.CategoryFragment

class CategoryPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun createFragment(position: Int): Fragment {
        return CategoryFragment(position)
    }
}