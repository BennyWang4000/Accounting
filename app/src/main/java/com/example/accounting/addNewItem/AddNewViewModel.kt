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
import com.example.accounting.database.ListDatabase
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

    var title: MutableLiveData<String>
    var note: MutableLiveData<String>

    init {
        val listDao = ListDatabase.getDatabase(application, viewModelScope).getListDao()
        repository = Repository(listDao)
        selectedDate= RepositoryDate.selectedDate

        operand1= calculator.operand1
        operand2= calculator.operand2
        isOperating= calculator.isOperating

        title= MutableLiveData("")
        note= MutableLiveData("")

        Log.d(TAG,  "Add New View Model Date: ${selectedDate.value.toString()}")
    }

    fun insertItem(item: ItemEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertItem(item)
    }

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