package com.example.accounting.main

import android.app.Application
import android.content.ClipData
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.accounting.room.ItemEntity
import com.example.accounting.room.ListDatabase
import com.example.accounting.Repository
import java.time.LocalDate
import com.example.accounting.Repository.Date as RepositoryDate

class MainViewModel (application: Application): AndroidViewModel(application) {
    //建立 repository 實體
    private val repository: Repository

    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val dateItem: LiveData<List<ItemEntity>>

    var currentDate: MutableLiveData<LocalDate>
    var selectedDate: MutableLiveData<LocalDate>

    init {
        val listDao = ListDatabase.getDatabase(application, viewModelScope).getListDao()
        repository = Repository(listDao)

        currentDate= RepositoryDate.currentDate
        selectedDate= RepositoryDate.selectedDate

        dateItem = repository.getDateItem(selectedDate.value.toString())
    }

//    fun upDateRepository(){
//        repository.selectedDate.value= this.selectedDate.value
//    }


    fun getSum(): Int{
        var sum: Int= 0

        for(i in 0..dateItem.value!!.size){
            sum+= dateItem.value!!.get(i).price
        }
        Log.d(TAG, "sum: $sum")
        return sum
    }

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

//
//    var currentDate= repository.currentDate
//    var selectedDate= repository.selectedDate


//    fun getNewItem(intent: Intent){
//
//    }
}
