package com.example.accounting.addNewItem

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.accounting.Repository
import com.example.accounting.Calculator
import com.example.accounting.database.model.ExpenseEntity
import com.example.accounting.database.AccountingDatabase
import com.example.accounting.database.model.CategoryEntity
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

    var descr: MutableLiveData<String>
    var account: MutableLiveData<Int>

    var categories: LiveData<List<CategoryEntity>>

    init {
        val accountingDao = AccountingDatabase.getDatabase(application, viewModelScope).getAccountingDao()
        repository = Repository(accountingDao)

        selectedDate= RepositoryDate.selectedDate

        operand1= calculator.operand1
        operand2= calculator.operand2
        isOperating= calculator.isOperating

        categories= repository.getAllCategories()

        descr= MutableLiveData("")
        account= MutableLiveData(0)

        Log.d(TAG,  "Add New View Model Date: ${selectedDate.value.toString()}")
    }

    //getter
    fun getAllCategories(): LiveData<List<CategoryEntity>> = this.categories

    // to database
    fun insertItem(expense: ExpenseEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertItem(expense)
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