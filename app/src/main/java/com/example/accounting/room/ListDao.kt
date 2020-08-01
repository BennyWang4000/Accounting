package com.example.accounting.room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.time.LocalDate

/**
 * 方案：
 *      1. 一個 database 一個 table ，全部塞進同一個資料表
 *          -目前使用
 *          -認知中會造成多餘的查詢
 *          -最好就是指查日期
 *
 *      2. 一個 database ??個 table ，新增資料時，若沒有以該日期命名之資料表，則建立並放進資料
 *          -採用 : ALTER TABLE *** RENAME TO $date / (:data)
 *          -問題 : An annotation argument must be a compile-time constant
 *          -問題 : 反正就一堆問題
 *
 *      3. 一個 database 一個 table 包著??個 table，兩個欄位：一個日期、一個是放 item 的資料表
 *          -巢狀資料表可以ㄇ 🤔🤔🤔
 * */

@Dao()
interface ListDao {
//    var date: String= "20000101"

//    @Query("SELECT * from date_table ORDER BY date ASC")
//    fun getAllDate(): LiveData<List<DateEntity>>


    @Query("SELECT * FROM item_table")
    fun getAllItems(): LiveData<List<ItemEntity>>

    @Query("SELECT * FROM item_table WHERE date IN (:date) ORDER BY id ASC")
    fun getDateItems(date: String): LiveData<List<ItemEntity>>

//    @Query("ALTER TABLE item_table RENAME TO $date")
//    fun alterTableName(date: Int)

//    @Query("ALTER TABLE new_item_table RENAME TO (:date)")
//    fun renameTable(date: String): LiveData<List<ItemEntity>>

//    @Query("SELECT * FROM date_table WHERE ")
//    fun getDateItem(date: String):

//    @Query("SELECT * FROM item_table WHERE date IN (:date)")
//    fun getDateItem(date: String): LiveData<List<ItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: ItemEntity)
}