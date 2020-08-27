package com.example.accounting.setting.category.categoryFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.accounting.setting.category.CategoryViewModel

class CategoryFragmentViewModelFactory (
    val application: Application,
    val behavior: Int
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryFragmentViewModel(
            application,
            behavior
        ) as T
    }
}