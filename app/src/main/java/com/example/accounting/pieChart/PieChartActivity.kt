package com.example.accounting.pieChart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.content.res.AppCompatResources
import androidx.viewpager2.widget.ViewPager2
import com.example.accounting.R
import com.example.accounting.pieChart.adapter.PieChartPagerAdapter

class PieChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pie_chart_activity)

        //tool bar
        val toolbar= findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_pie)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}