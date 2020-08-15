package com.example.accounting.main.listFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.accounting.Repository
import com.example.accounting.database.model.ItemEntity
import com.example.accounting.database.ListDatabase
import java.time.LocalDate
import com.example.accounting.Repository.Date as RepositoryDate

class ListViewModel(application: Application): AndroidViewModel(application) {
    //建立 repository 實體
     val repository: Repository
    var selectedDateData: LiveData<List<ItemEntity>>
    var privousDayData: LiveData<List<ItemEntity>>
    var nextDayData: LiveData<List<ItemEntity>>

    var currentDate: MutableLiveData<LocalDate>
    var selectedDate: MutableLiveData<LocalDate>

    var currentPosition: MutableLiveData<Int>
    var lastPosition= RepositoryDate.lastPosition

    private val PAGER_LIST_MAX_VALUE= Int.MAX_VALUE
    private val PAGER_LIST_MID_POSITION= Int.MAX_VALUE/ 2

    init {
        val listDao = ListDatabase.getDatabase(application, viewModelScope).getListDao()
        repository = Repository(listDao)

        currentDate= RepositoryDate.currentDate
        selectedDate= RepositoryDate.selectedDate

        selectedDateData = repository.getDateItem(selectedDate.value.toString())
        privousDayData = repository.getDateItem(selectedDate.value!!.plusDays(-1).toString())
        nextDayData = repository.getDateItem(selectedDate.value!!.plusDays(1).toString())

        currentPosition= RepositoryDate.currentPosition
    }


    fun getDateData(position: Int): LiveData<List<ItemEntity>>{
//        return when{
//            position> lastPosition.value!! ->{
//                Log.d(ContentValues.TAG, "position $position> lastPosition.value ${lastPosition.value}")
//                privousDayData
//            }
//            position< lastPosition.value!! -> {
//                Log.d(ContentValues.TAG, "position $position> lastPosition.value${lastPosition.value}")
//                nextDayData
//            }
//            else -> {
//                Log.d(ContentValues.TAG, "position $position== lastPosition.value ${lastPosition.value}")
//                selectedDateData
//            }
//
//        }
        return selectedDateData
    }

    fun getRecyclerViewData(position: Int): LiveData<List<ItemEntity>>{
        return repository.getDateItem(selectedDate.value!!.plusDays((PAGER_LIST_MAX_VALUE- position).toLong()).toString())
    }

    fun pageChanged(){
//        selectedDateData = repository.getDateItem(selectedDate.value.toString())
//        privousDayData = repository.getDateItem(selectedDate.value!!.plusDays(-1).toString())
//        nextDayData = repository.getDateItem(selectedDate.value!!.plusDays(1).toString())
    }
}