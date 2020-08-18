package com.example.accounting.main

import android.app.Application
import androidx.lifecycle.*
import com.example.accounting.Repository
import com.example.accounting.database.model.ItemEntity
import com.example.accounting.database.AccountingDatabase
import java.time.LocalDate

class MainDrawerViewModel(application: Application): AndroidViewModel(application) {
    //建立 repository 實體
    private val repository: Repository

    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val dateItem: LiveData<List<ItemEntity>>

    var currentDate: MutableLiveData<LocalDate>
    var selectedDate: MutableLiveData<LocalDate>

    var pagePosition= MutableLiveData<Int>(Int.MAX_VALUE/ 2)

    var lastPosition= Repository.lastPosition
    var currentPosition= Repository.currentPosition

    var dateId: MutableLiveData<Int>

    init {
        val listDao = AccountingDatabase.getDatabase(application, viewModelScope).getItemDao()
        repository = Repository(listDao)

        currentDate= Repository.currentDate
        selectedDate= Repository.selectedDate

        dateId= MutableLiveData(repository.getDateId(selectedDate.value.toString()).value!![0].id)

        dateItem = repository.getDateItem(dateId.value!!)
    }

}