package com.example.accounting.setting.budget

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.accounting.Calculator
import com.example.accounting.Repository
import com.example.accounting.database.AccountingDatabase
import com.example.accounting.database.model.SettingsEntity
import java.time.LocalDate

class BudgetViewModel(application: Application): AndroidViewModel(application) {
    private val repository: Repository

    var selectedDate: MutableLiveData<LocalDate>
    private val calculator= Calculator()
    var operand1: MutableLiveData<String>
    var operand2: MutableLiveData<String>
    var isOperating: MutableLiveData<Boolean>

    var settings: LiveData<List<SettingsEntity>>

    init {
        val accountingDao = AccountingDatabase.getDatabase(application, viewModelScope).getAccountingDao()
        repository = Repository(accountingDao)

        selectedDate= Repository.selectedDate

        operand1= calculator.operand1
        operand2= calculator.operand2
        isOperating= calculator.isOperating

        settings= repository.getSetting()
    }

    // to database
    fun getSetting(): LiveData<List<SettingsEntity>>{
        return repository.getSetting()
    }
    fun updateBudgetAmount(){
        repository.updateBudgetAmount(operand1.value!!.toInt())
    }
    fun updateBudgetShow(isShow: Boolean){
        repository.updateBudgetShow(isShow)
    }

    // to calculator===============
    fun clickNum(num: String){
        calculator.clickNumber(num)
    }

    fun clickOperator(operator: Int){
        calculator.clickOperator(operator)
    }

    fun clickAC(){
        calculator.clickAC()
    }

    fun clickOK(): String{
        return calculator.clickOK()
    }

    fun clickBackspace(){
        calculator.clickBackspace()
    }
}