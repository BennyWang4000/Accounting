package com.example.accounting

import android.content.ContentValues.TAG
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.accounting.database.model.ItemEntity
import com.example.accounting.database.dao.ListDao
import java.time.LocalDate
import java.time.format.DateTimeFormatter

//負責 view model 和 Dao / database 之間的資料使用
class Repository(private val listDao: ListDao) {

    companion object Date {
        private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        var currentDate = MutableLiveData<LocalDate>(LocalDate.parse(LocalDate.now().toString()
            , dateFormatter))
        var selectedDate = MutableLiveData<LocalDate>(LocalDate.parse(LocalDate.now().toString()
            , dateFormatter))
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
    suspend fun insertItem(item: ItemEntity) {
        //使用 dao 的方法
        Log.d(TAG, item.toString())
        listDao.insertItem(item)
    }

    fun getDateItem(date: String): LiveData<List<ItemEntity>> {
        return listDao.getDateItems(date)
    }

//    fun  getDateSum(date: String): LiveData<List<DateEntity>>

    fun getAllItem(): LiveData<List<ItemEntity>>{
        return listDao.getAllItems()
    }

//    fun getPieData(): LiveData<List<PieEntry>>{
//        return listDao.getPieChartData()
//    }



//    private var isCreated = false
//    var currentDate = MutableLiveData<LocalDate>()
//    var selectedDate = MutableLiveData<LocalDate>()
//
//    init {
//        val dateFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd")
//        currentDate.value= LocalDate.parse(LocalDate.now().toString(), dateFormatter)
//
//        if (!isCreated) {
//            selectedDate.value= LocalDate.parse(LocalDate.now().toString(), dateFormatter)
//            Log.d(TAG, "Created")
//            isCreated= true
//        }
//        Log.d(TAG, "")
//    }

//    val allData: LiveData<List<ItemEntity>> = listDao.getAllItems()



}