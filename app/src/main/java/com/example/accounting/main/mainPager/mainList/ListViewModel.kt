package com.example.accounting.main.listFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.accounting.Repository
import com.example.accounting.database.model.ExpenseEntity
import com.example.accounting.database.AccountingDatabase
import java.time.LocalDate
import com.example.accounting.Repository.Date as RepositoryDate

class ListViewModel(application: Application, val position: Int): AndroidViewModel(application) {
    //建立 repository 實體
    private val repository: Repository
    var pageDateData: LiveData<List<ExpenseEntity>>

    var currentDate: MutableLiveData<LocalDate>
    var selectedDate: MutableLiveData<LocalDate>

    var pageDate: LocalDate

    var currentPosition: MutableLiveData<Int>
    var lastPosition= RepositoryDate.lastPosition

    private val PAGER_LIST_MAX_VALUE= Int.MAX_VALUE
    private val PAGER_LIST_MID_POSITION= Int.MAX_VALUE/ 2

    init {
        val listDao = AccountingDatabase.getDatabase(application, viewModelScope).getAccountingDao()
        repository = Repository(listDao)

        currentDate= RepositoryDate.currentDate
        selectedDate= RepositoryDate.selectedDate

        pageDate= selectedDate.value!!.plusDays((position- PAGER_LIST_MID_POSITION).toLong())
        pageDateData= repository.getDailyExpenses(pageDate.toString())

        if(repository.getDailyExpenses(pageDate.toString()).value== null) {
            Log.d("pageDate:", "NULL DATA..")
        }


        currentPosition= RepositoryDate.currentPosition
    }


    fun getDateData(): LiveData<List<ExpenseEntity>>{
        return pageDateData
    }

    fun getSum(): Double{
        return pageDateData.value!!.sumByDouble { it.amount }
    }
}