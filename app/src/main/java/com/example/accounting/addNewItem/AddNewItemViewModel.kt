package com.example.accounting.addNewItem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.accounting.ListRepository
import com.example.accounting.room.ItemEntity
import com.example.accounting.room.ListDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewItemViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ListRepository
    val data: LiveData<List<ItemEntity>>

    init {
        val listDao = ListDatabase.getDatabase(application, viewModelScope).getListDao()
        repository = ListRepository(listDao)
        data = repository.allData
    }

    fun insertItem(item: ItemEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertItem(item)
    }
//    suspend fun insertItem(item: ItemEntity){
//        repository.insertItem(item)
//    }
}