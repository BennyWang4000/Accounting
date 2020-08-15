package com.example.accounting.pieChart

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.accounting.Repository
import com.example.accounting.database.model.ItemEntity
import com.example.accounting.database.ListDatabase
import com.github.mikephil.charting.data.PieEntry
import java.time.LocalDate

class PieChartViewModel(application: Application): AndroidViewModel(application){
    private val repository: Repository

    var selectedDate: LiveData<LocalDate>
    var allData: LiveData<List<ItemEntity>>
//    var pieChartData: LiveData<List<PieEntry>>

    var sumData= listOf<PieEntry>()

    init {
        val listDao = ListDatabase.getDatabase(application, viewModelScope).getListDao()
        repository = Repository(listDao)

        selectedDate= Repository.selectedDate
        allData= repository.getAllItem()
        repository.getAllItem()
//        pieChartData= repository.getPieData()
    }

    //    fun getPieData(upper: LocalDate, lower: LocalDate): List<PieEntry>{
    fun getPieData(): List<PieEntry>{
        val mData= mutableListOf<PieEntry>()
        for(i in allData.value!!.indices){
            mData.add(PieEntry(allData.value!![i].price.toFloat(), allData.value!![i].date))
        }
        val mmData= mData.groupBy { it.label }
        sumData=
            mmData.values.map {
                it.reduce { acc, list ->
                    PieEntry(acc.value+ list.value)
                }
            }

        Log.d("PIE DATA", sumData.toString())
        return sumData
    }

    fun getPieDataSum(): Int{
        val mSum: Int
        return allData.value!!.sumBy { it.price }
    }




}