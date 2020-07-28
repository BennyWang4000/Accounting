package com.example.accounting.main.listFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.accounting.Repository
import com.example.accounting.room.ItemEntity
import com.example.accounting.room.ListDatabase

class ListViewModel(application: Application): AndroidViewModel(application) {
    //建立 repository 實體
    private val repository: Repository
    val allData: LiveData<List<ItemEntity>>

    init {
        val listDao = ListDatabase.getDatabase(application, viewModelScope).getListDao()
        repository = Repository(listDao)
        allData = repository.allData
        Log.d("test", "MainViewModel allData: ${allData.value.toString()}")
    }

}