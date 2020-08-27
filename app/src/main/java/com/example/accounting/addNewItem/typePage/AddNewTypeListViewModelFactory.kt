package com.example.accounting.addNewItem.typePage

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddNewTypeListViewModelFactory(
    val application: Application,
    private val page: Int
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddNewTypeListViewModel(
            application,
            page
        ) as T
    }
}