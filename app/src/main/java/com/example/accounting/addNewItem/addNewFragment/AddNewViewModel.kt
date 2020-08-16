package com.example.accounting.addNewItem.addNewFragment

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.accounting.Repository
import com.example.accounting.database.model.ItemEntity
import com.example.accounting.database.ListDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import com.example.accounting.Repository.Date as RepositoryDate

class AddNewViewModel(application: Application): AndroidViewModel(application) {
    private val repository: Repository

    var selectedDate: MutableLiveData<LocalDate>

    init {
        val listDao = ListDatabase.getDatabase(application, viewModelScope).getListDao()
        repository = Repository(listDao)
        selectedDate= RepositoryDate.selectedDate

        Log.d(TAG,  "Add New View Model Date: ${selectedDate.value.toString()}")
    }

    fun insertItem(item: ItemEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertItem(item)
    }
}