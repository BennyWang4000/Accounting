package com.example.accounting.main.mainPager.mainList

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.accounting.main.listFragment.ListViewModel

class ListViewModelFactory(val application: Application, val position: Int): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(application, position) as T
    }
}