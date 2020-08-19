package com.example.accounting.main.listFragment.mainFragment

import android.app.Application
import androidx.lifecycle.*
import com.example.accounting.database.AccountingDatabase
import com.example.accounting.Repository
import java.time.LocalDate
import com.example.accounting.Repository.Date as RepositoryDate

class MainPagerViewModel (application: Application): AndroidViewModel(application) {
    //建立 repository 實體
    private val repository: Repository

    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
//    val dailyExpense: LiveData<List<ExpenseEntity>>

    var currentDate: MutableLiveData<LocalDate>
    var selectedDate: MutableLiveData<LocalDate>

    var pagePosition= MutableLiveData<Int>(Int.MAX_VALUE/ 2)

    var lastPosition= RepositoryDate.lastPosition
//    var currentPosition= RepositoryDate.currentPosition


    init {
        val listDao = AccountingDatabase.getDatabase(application, viewModelScope).getAccountingDao()
        repository = Repository(listDao)

        currentDate= RepositoryDate.currentDate
        selectedDate= RepositoryDate.selectedDate

//        dailyExpense = repository.getDateItem(selectedDate.value.toString())
    }

//    fun upDateRepository(){
//        repository.selectedDate.value= this.selectedDate.value
//    }





    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
//    fun insertItem(intent: Intent) = viewModelScope.launch(Dispatchers.IO) {
//        val item= ItemEntity(
//            ""+ getCurrentDate()[0]+ getCurrentDate()[1]+ getCurrentDate()[2],
//            id++,
//            "title",
//            "body",
//            "type",
//            intent.getStringExtra("price").toInt()
//        )
//        repository.insertItem(item)
//    }
}
