package com.example.accounting.main.listFragment

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.accounting.Repository
import com.example.accounting.room.ItemEntity
import com.example.accounting.room.ListDatabase
import java.time.LocalDate
import com.example.accounting.Repository.Date as RepositoryDate

class ListViewModel(application: Application): AndroidViewModel(application) {
    //建立 repository 實體
    private val repository: Repository
    var selectedDateData: LiveData<List<ItemEntity>>
//    var privousDayData: LiveData<List<ItemEntity>>
//    var nextDayData: LiveData<List<ItemEntity>>

    var currentDate: MutableLiveData<LocalDate>
    var selectedDate: MutableLiveData<LocalDate>

//    var lastPosition= RepositoryDate.lastPosition

    private val PAGER_LIST_MAX_VALUE= 3
    private val PAGER_LIST_MID_POSITION= 1

    init {
        val listDao = ListDatabase.getDatabase(application, viewModelScope).getListDao()
        repository = Repository(listDao)

        currentDate= RepositoryDate.currentDate
        selectedDate= RepositoryDate.selectedDate

        selectedDateData = repository.getDateItem(selectedDate.value.toString())
//        privousDayData = repository.getDateItem(selectedDate.value!!.plusDays(-1).toString())
//        nextDayData = repository.getDateItem(selectedDate.value!!.plusDays(1).toString())

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

    fun pageChanged(){
//        selectedDateData = repository.getDateItem(selectedDate.value.toString())
//        privousDayData = repository.getDateItem(selectedDate.value!!.plusDays(-1).toString())
//        nextDayData = repository.getDateItem(selectedDate.value!!.plusDays(1).toString())
    }
}