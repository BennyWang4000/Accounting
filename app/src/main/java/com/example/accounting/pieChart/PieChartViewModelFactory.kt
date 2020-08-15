package com.example.accounting.pieChart

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class PieChartViewModelFactory(val application: Application): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PieChartViewModel(application) as T
    }
}