package com.example.accounting.setting.category.categoryFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.accounting.Calculator
import com.example.accounting.Repository
import com.example.accounting.database.AccountingDatabase
import com.example.accounting.database.model.CategoryEntity
import com.example.accounting.database.model.SettingsEntity
import java.time.LocalDate

class CategoryFragmentViewModel(application: Application, val behavior: Int): AndroidViewModel(application) {
    private val repository: Repository

    var settings: LiveData<List<SettingsEntity>>
    var behaviorCategories: LiveData<List<CategoryEntity>>

    init {
        val accountingDao = AccountingDatabase.getDatabase(application, viewModelScope).getAccountingDao()
        repository = Repository(accountingDao)

        settings= repository.getSetting()
        behaviorCategories= repository.getBehaviorCategories(behavior)
    }
}