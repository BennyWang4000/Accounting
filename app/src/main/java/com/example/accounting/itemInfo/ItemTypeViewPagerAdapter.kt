package com.example.accounting.itemInfo

import android.view.View
import androidx.viewpager.widget.PagerAdapter

class ItemTypeViewPagerAdapter : PagerAdapter(){

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return 2
    }
}