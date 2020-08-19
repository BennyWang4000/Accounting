package com.example.accounting.itemInfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.accounting.Repository
import com.example.accounting.database.AccountingDatabase

class ItemInfoViewModel(application: Application) : AndroidViewModel(application){
    private val repository: Repository
    init {
        val listDao = AccountingDatabase.getDatabase(application, viewModelScope).getAccountingDao()
        repository = Repository(listDao)

    }
}