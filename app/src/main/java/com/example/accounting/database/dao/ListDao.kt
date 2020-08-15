package com.example.accounting.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.accounting.database.model.ItemEntity

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

@Dao
interface ListDao {
    @Query("SELECT * FROM item_table ORDER BY date ASC")
    fun getAllItems(): LiveData<List<ItemEntity>>

    @Query("SELECT * FROM item_table WHERE date IN (:date) ORDER BY id ASC")
    fun getDateItems(date: String): LiveData<List<ItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: ItemEntity)


}

