package com.example.accounting.pieChart

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.R
import com.example.accounting.TypeStyle
import com.example.accounting.pieChart.adapter.PieChartTypeRankAdapter
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet

class PieChartFragment : Fragment() {
    private lateinit var viewModel: PieChartViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root=  inflater.inflate(R.layout.pie_chart_fragment, container, false)

        val factory= PieChartViewModelFactory(activity!!.application)
        viewModel= ViewModelProvider(this, factory).get(PieChartViewModel::class.java)

//recyclerview
        val recyclerView= root.findViewById<RecyclerView>(R.id.rv_type_rank)
        val recyclerViewAdapter= PieChartTypeRankAdapter(viewModel, context!!)
        recyclerView.adapter= recyclerViewAdapter

        recyclerViewAdapter.notifyDataSetChanged()

        //observe
        viewModel.allData.observe(this, Observer {
            //pie chart
            Log.e("test", "observe") //chart
            val pieChart: PieChart= root.findViewById(R.id.chart_pie)

            val pieDataSet= PieDataSet(viewModel.getPieData(), "expense").apply{
                colors = TypeStyle.COLOR_RANK
                valueTextColor= Color.WHITE
                valueTextSize= 10f
            }
            val pieData= PieData(pieDataSet)

            with(pieChart){
                data= pieData
                description.isEnabled= false
                centerText= "支出\n${viewModel.getPieDataSum().toString()}"
                animate()
                isClickable= false
            }

            //position

//            //legend
            val legend= pieChart.legend.apply {
                isEnabled= false
            }
//            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
//            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
//            legend.orientation = Legend.LegendOrientation.VERTICAL

            recyclerViewAdapter.notifyDataSetChanged()
        })

        return root
    }
}