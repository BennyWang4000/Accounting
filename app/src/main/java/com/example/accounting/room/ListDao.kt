package com.example.accounting.room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * 方案：
 *      1. 一個 database 一個 table ，全部塞進同一個資料表
 *      2. 一個 database ??個 table ，新增資料時，若沒有以該日期命名之資料表，則建立並放進資料
 *          -使用 : ALTER TABLE *** RENAME TO $date
 *          -問題 : An annotation argument must be a compile-time constant
 *
 *      3. 一個 database 一個 table ，兩個欄位：一個日期、一個是放 item 的資料表
 *          -巢狀資料表可以ㄇ
 * */

@Dao()
interface ListDao {
//    var date: String= "20000101"

//    @Query("SELECT * from date_table ORDER BY date ASC")
//    fun getAllDate(): LiveData<List<DateEntity>>


    @Query("SELECT * FROM item_table")
    fun getAllItem(): LiveData<List<ItemEntity>>

//    @Query("ALTER TABLE item_table RENAME TO $date")
//    fun alterTableName(date: Int)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItem(item: ItemEntity)
}