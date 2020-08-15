package com.example.accounting.pieChart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.R
import com.example.accounting.pieChart.PieChartViewModel

class PieChartTypeRankAdapter(val viewModel: PieChartViewModel, val context: Context) : RecyclerView.Adapter<PieChartTypeRankAdapter.LegendViewHolder>(){
    inner class LegendViewHolder(item: View): RecyclerView.ViewHolder(item){
        val tvRank= item.findViewById<TextView>(R.id.tv_rank)
        val ivType= item.findViewById<TextView>(R.id.iv_type)
        val tvPercent= item.findViewById<TextView>(R.id.tv_percent)
        val tvName= item.findViewById<TextView>(R.id.tv_name)
        val tvPrice= item.findViewById<TextView>(R.id.tv_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LegendViewHolder {
        return LegendViewHolder(
            LayoutInflater.from(context).inflate(R.layout.pie_chart_recycler_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return viewModel.sumData.size
    }

    override fun onBindViewHolder(holder: LegendViewHolder, position: Int) {
        holder.ivType.text= (position+ 1).toString()

    }
}