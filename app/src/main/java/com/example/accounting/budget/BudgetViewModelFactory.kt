package com.example.accounting.budget

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.accounting.addNewItem.addNewFragment.AddNewViewModel

class BudgetViewModelFactory (
    val application: Application
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BudgetViewModel(
            application
        ) as T
    }
}