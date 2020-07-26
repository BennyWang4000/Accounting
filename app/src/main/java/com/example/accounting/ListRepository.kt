package com.example.accounting

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.accounting.room.ItemEntity
import com.example.accounting.room.ListDao

//負責 view model 和 Dao / database 之間的資料使用
class ListRepository(private val listDao: ListDao) {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    //以傳進之 dao 的方法，建立所有資料
    val data: LiveData<List<ItemEntity>> = listDao.getAllItem()

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
        //調用 dao 的方法
        listDao.insertItem(item)
    }
}