package com.example.accounting

import android.content.ContentValues.TAG
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.accounting.database.model.ExpenseEntity
import com.example.accounting.database.dao.AccountingDao
import com.example.accounting.database.model.CategoryEntity
import com.example.accounting.database.model.SettingsEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

//負責 view model 和 Dao / database 之間的資料使用
class Repository(private val accountingDao: AccountingDao) {

    companion object Date {
        private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        var currentDate = MutableLiveData<LocalDate>(LocalDate.parse(LocalDate.now().toString(), dateFormatter))
        var selectedDate = MutableLiveData<LocalDate>(LocalDate.parse(LocalDate.now().toString(), dateFormatter))
        var lastPosition= MutableLiveData<Int>(Int.MAX_VALUE/ 2)
        var currentPosition= MutableLiveData<Int>(Int.MAX_VALUE/ 2)
    }

    // You must call this on a non-UI thread or your app will crash. So we're making this a
    // suspend function so the caller methods know this.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    //消除警告
    @Suppress("RedundantSuspendModifier")
    //Thread 不是自動使用，而是透過特殊方法呼叫(?
    @WorkerThread
    //viewModel 使用之方法
    suspend fun insertItem(expense: ExpenseEntity) {
        //使用 dao 的方法
        Log.d(TAG, expense.toString())
        accountingDao.insertExpense(expense)
    }

    //expense_table--------------------------------------
    fun getDailyExpenses(date: String): LiveData<List<ExpenseEntity>> {
        return accountingDao.getDailyExpenses(date)
    }

    fun getMonthlyExpenses(month: String): LiveData<List<ExpenseEntity>>{
        return accountingDao.getMonthlyExpenses(month)
    }

//    fun  getDateSum(date: String): LiveData<List<DateEntity>>
    fun getAllItem(): LiveData<List<ExpenseEntity>>{
        return accountingDao.getAllExpenses()
    }

//    //date_table--------------------------------------
//    fun getDateId(date: String): LiveData<List<DateEntity>>{
//        return accountingDao.getDateId(date)
//    }
//    fun getDate(dateId: Int): LiveData<List<DateEntity>>{
//        return accountingDao.getDate(dateId)
//    }
//    fun insertDate(date: DateEntity){
//        accountingDao.insertDate(date)
//    }

    //type_table--------------------------------------
    fun getTypeId(type: String): LiveData<List<CategoryEntity>>{
        return accountingDao.getTypeId(type)
    }


    //setting_table--------------------------------------
    fun getSetting(): LiveData<List<SettingsEntity>>{
        return accountingDao.getSettings()
    }


    fun insertSetting(setting: SettingsEntity){
        accountingDao.insertSetting(setting)
    }
    fun updateBudgetAmount(budget: Int){
//        accountingDao.updateBudgetAmount(budget)
        accountingDao.updateBudgetAmount(budget)
    }
    fun updateBudgetShow(isShow: Boolean){
        accountingDao.updateBudgetShow(isShow)
    }

}