package com.example.accounting.addNewItem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.accounting.Repository
import com.example.accounting.room.ItemEntity
import com.example.accounting.room.ListDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewViewModel(application: Application): AndroidViewModel(application) {
    private val repository: Repository
    val data: LiveData<List<ItemEntity>>

    init {
        val listDao = ListDatabase.getDatabase(application, viewModelScope).getListDao()
        repository = Repository(listDao)
        data = repository.allData
    }

    fun insertItem(item: ItemEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertItem(item)
    }
}