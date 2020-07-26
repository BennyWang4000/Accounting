package com.example.accounting.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * 兩個方案
 *      1. 一個 database 一個 table ，全部塞進同一個資料表
 *      2. 一個 database 兩個 table ，一個放有資料的日期和該日期的資料數量
 *      3. 一個 database ??個 table，新增資料時，若沒有以該日期命名之資料表，則建立並放進資料
 *          -問題 : An annotation argument must be a compile-time constant
 * */

@Dao()
interface ListDao {
//    var date: String= "20000101"

//    @Query("SELECT * from date_table ORDER BY date ASC")
//    fun getAllDate(): LiveData<List<DateEntity>>


    @Query("SELECT * from item_table ORDER BY date, id ASC")
    fun getAllItem(): LiveData<List<ItemEntity>>

//    @Query("ALTER TABLE item_table RENAME TO $date")
//    fun alterTableName(date: Int)

    @Insert
    fun insertItem(item: ItemEntity)
}