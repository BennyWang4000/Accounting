package com.example.accounting.main

import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel: ViewModel() {
    fun setDate(date: List<String>){

    }

    fun getCurrentDate(): List<Int> = listOf(
        Calendar.getInstance().get(Calendar.YEAR),
        Calendar.getInstance().get(Calendar.MONTH),
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    )
}