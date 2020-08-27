package com.example.accounting.pieChart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.accounting.R

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