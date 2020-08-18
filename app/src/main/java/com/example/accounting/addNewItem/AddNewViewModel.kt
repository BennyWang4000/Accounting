package com.example.accounting.addNewItem.addNewFragment

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.accounting.Repository
import com.example.accounting.addNewItem.Calculator
import com.example.accounting.database.model.ItemEntity
import com.example.accounting.database.AccountingDatabase
import com.example.accounting.database.model.DateEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import com.example.accounting.Repository.Date as RepositoryDate

class AddNewViewModel(application: Application): AndroidViewModel(application) {
    private val repository: Repository

    var selectedDate: MutableLiveData<LocalDate>
    private val calculator= Calculator()
    var operand1: MutableLiveData<String>
    var operand2: MutableLiveData<String>
    var isOperating: MutableLiveData<Boolean>

    var price: MutableLiveData<Double>
    var title: MutableLiveData<String>
    var note: MutableLiveData<String>

    val dateId: MutableLiveData<Int>

    init {
        val accountingDao = AccountingDatabase.getDatabase(application, viewModelScope).getItemDao()
        repository = Repository(accountingDao)

        selectedDate= RepositoryDate.selectedDate

        dateId= MutableLiveData(repository.getDateId(selectedDate.value.toString()).value!![0].id)

        operand1= calculator.operand1
        operand2= calculator.operand2
        isOperating= calculator.isOperating

        price= MutableLiveData(0.0)
        title= MutableLiveData("")
        note= MutableLiveData("")

        Log.d(TAG,  "Add New View Model Date: ${selectedDate.value.toString()}")
    }

    // to database
    fun insertItem(item: ItemEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertItem(item)
    }
    fun getDateId(date: String): Int{
        return -1
    }
    fun insertDate(date: DateEntity){
        repository.insertDate(date)
    }
    fun getTypeId(type: String): Int{
        return repository.getTypeId(type).value!![0].id
    }

    // to calculator
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