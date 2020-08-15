package com.example.accounting.pieChart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.accounting.R
import com.example.accounting.pieChart.adapter.PieChartPagerAdapter

class PieChartPagerContainer: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root=  inflater.inflate(R.layout.pie_chart_container, container, false)

        val viewPager= root.findViewById<ViewPager2>(R.id.pager_pie_chart)
        val pagerAdapter= PieChartPagerAdapter(activity!!)
        viewPager.adapter= pagerAdapter

        return root
    }
}