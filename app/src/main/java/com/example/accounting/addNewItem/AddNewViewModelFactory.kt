package com.example.accounting.addNewItem.addNewFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.accounting.addNewItem.AddNewViewModel

class AddNewViewModelFactory(
    val application: Application
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddNewViewModel(
            application
        ) as T
    }
}