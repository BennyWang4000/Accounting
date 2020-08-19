package com.example.accounting.main

import android.app.Application
import androidx.lifecycle.*
import com.example.accounting.Repository
import com.example.accounting.database.AccountingDatabase
import java.time.LocalDate

class MainDrawerViewModel(application: Application): AndroidViewModel(application) {
    //建立 repository 實體
    private val repository: Repository

    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    var currentDate: MutableLiveData<LocalDate>
    var selectedDate: MutableLiveData<LocalDate>


    init {
        val listDao = AccountingDatabase.getDatabase(application, viewModelScope).getAccountingDao()
        repository = Repository(listDao)

        currentDate= Repository.currentDate
        selectedDate= Repository.selectedDate
    }

}