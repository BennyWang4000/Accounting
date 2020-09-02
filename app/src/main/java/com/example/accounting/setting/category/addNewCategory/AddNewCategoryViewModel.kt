package com.example.accounting.setting.category.addNewCategory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.accounting.Repository
import com.example.accounting.database.AccountingDatabase
import com.example.accounting.database.model.CategoryEntity
import com.example.accounting.database.model.SettingsEntity

class AddNewCategoryViewModel(application: Application, behavior: Int): AndroidViewModel(application) {
    private val repository: Repository

    var settings: LiveData<List<SettingsEntity>>
    var behaviorCategories: LiveData<List<CategoryEntity>>

    init {
        val accountingDao = AccountingDatabase.getDatabase(application, viewModelScope).getAccountingDao()
        repository = Repository(accountingDao)

        settings= repository.getSetting()
        behaviorCategories= repository.getBehaviorCategories(behavior)
    }

    //to database
    fun insertCategory(category: CategoryEntity){
        repository.insertCategory(category)
    }
}