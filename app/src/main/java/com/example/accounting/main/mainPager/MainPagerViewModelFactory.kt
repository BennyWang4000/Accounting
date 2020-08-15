package com.example.accounting.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.accounting.main.listFragment.mainFragment.MainPagerViewModel

class MainPagerViewModelFactory(var application: Application): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainPagerViewModel(
            application
        ) as T
    }
}