package com.example.accounting.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.accounting.room.ItemEntity
import com.example.accounting.room.ListDatabase
import com.example.accounting.ListRepository
import java.util.*

class MainViewModel (application: Application): AndroidViewModel(application) {
    //建立 repository 實體
    private val repository: ListRepository

    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allData: LiveData<List<ItemEntity>>


    init {
        val listDao = ListDatabase.getDatabase(application, viewModelScope).getListDao()
        repository = ListRepository(listDao)
        allData = repository.allData
        Log.e("test", allData.toString())
    }


    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
//    fun insertItem(intent: Intent) = viewModelScope.launch(Dispatchers.IO) {
//        val item= ItemEntity(
//            ""+ getCurrentDate()[0]+ getCurrentDate()[1]+ getCurrentDate()[2],
//            id++,
//            "title",
//            "body",
//            "type",
//            intent.getStringExtra("price").toInt()
//        )
//        repository.insertItem(item)
//    }

    fun getCurrentDate(): List<Int> = listOf(
        Calendar.getInstance().get(Calendar.YEAR),
        Calendar.getInstance().get(Calendar.MONTH),
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    )

//    fun getNewItem(intent: Intent){
//
//    }
}