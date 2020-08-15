package com.example.accounting.main

import android.app.Application
import androidx.lifecycle.*
import com.example.accounting.Repository
import com.example.accounting.database.model.ItemEntity
import com.example.accounting.database.ListDatabase
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

    init {
        val listDao = ListDatabase.getDatabase(application, viewModelScope).getListDao()
        repository = Repository(listDao)

        currentDate= Repository.currentDate
        selectedDate= Repository.selectedDate

        dateItem = repository.getDateItem(selectedDate.value.toString())
    }

}