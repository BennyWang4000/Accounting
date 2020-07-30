package com.example.accounting.main.listFragment

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.accounting.Repository
import com.example.accounting.room.ItemEntity
import com.example.accounting.room.ListDatabase
import java.time.LocalDate
import com.example.accounting.Repository.Date as RepositoryDate

class ListViewModel(application: Application): AndroidViewModel(application) {
    //建立 repository 實體
    private val repository: Repository
    val dateData: LiveData<List<ItemEntity>>

    var currentDate: MutableLiveData<LocalDate>
    var selectedDate: MutableLiveData<LocalDate>

    init {
        val listDao = ListDatabase.getDatabase(application, viewModelScope).getListDao()
        repository = Repository(listDao)

        currentDate= RepositoryDate.currentDate
        selectedDate= RepositoryDate.selectedDate

        dateData = repository.getDateItem(selectedDate.value.toString())
        Log.d(ContentValues.TAG, "MainViewModel dateData: ${dateData.value.toString()}")
    }

}