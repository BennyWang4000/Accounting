package com.example.accounting

import android.content.ContentValues.TAG
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.accounting.room.ItemEntity
import com.example.accounting.room.ListDao
import java.time.LocalDate
import java.time.format.DateTimeFormatter

//負責 view model 和 Dao / database 之間的資料使用
class Repository(private val listDao: ListDao) {

    var currentDate= MutableLiveData<LocalDate>()
    var selectedDate= MutableLiveData<LocalDate>()

    init {
        val dateFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd")
        currentDate.value= LocalDate.parse(LocalDate.now().toString(), dateFormatter)
        selectedDate.value= LocalDate.parse(LocalDate.now().toString(), dateFormatter)
        Log.d(TAG, "")
    }

    val allData: LiveData<List<ItemEntity>> = listDao.getAllItems()


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
        Log.d(TAG, listDao.getDateItems(date.toString()).value.toString())
        return listDao.getDateItems(date)
    }
}