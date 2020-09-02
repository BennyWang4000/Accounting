package com.example.accounting.setting.category.addNewCategory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.accounting.setting.category.categoryFragment.CategoryFragmentViewModel

class AddNewCategoryViewModelFactory  (
    val application: Application,
    val behavior: Int
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddNewCategoryViewModel(
            application,
            behavior
        ) as T
    }
}