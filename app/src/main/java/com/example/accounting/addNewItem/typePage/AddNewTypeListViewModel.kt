package com.example.accounting.addNewItem.typePage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.accounting.Repository
import com.example.accounting.database.AccountingDatabase
import com.example.accounting.database.model.CategoryEntity

class AddNewTypeListViewModel(application: Application, val page: Int): AndroidViewModel(application) {
    private val repository: Repository

    var pageCategories: LiveData<List<CategoryEntity>>

    var selectedCategoryId: MutableLiveData<Int>

    init {
        val accountingDao = AccountingDatabase.getDatabase(application, viewModelScope).getAccountingDao()
        repository = Repository(accountingDao)

        selectedCategoryId= MutableLiveData(-1)

        //started id = page* 10+ 1
        //started id:  1 11 21
        //      page:  0  1  2
        pageCategories= repository.getPageCategories(page* 10+ 1)
    }
}