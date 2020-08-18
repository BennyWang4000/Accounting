package com.example.accounting.addNewItem

import androidx.lifecycle.MutableLiveData

class NewItem(
    var price: MutableLiveData<Double>,
    var title: MutableLiveData<Double>,
    var note: MutableLiveData<Double>
) {

    fun isValid(){

    }
}