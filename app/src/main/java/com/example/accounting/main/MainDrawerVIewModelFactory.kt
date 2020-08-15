package com.example.accounting.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainDrawerVIewModelFactory(var application: Application): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainDrawerViewModel(
            application
        ) as T
    }
}