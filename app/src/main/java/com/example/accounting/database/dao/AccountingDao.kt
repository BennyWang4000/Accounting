package com.example.accounting.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.accounting.database.model.DateEntity
import com.example.accounting.database.model.ItemEntity
import com.example.accounting.database.model.TypeEntity
import java.time.LocalDate

@Dao
interface AccountingDao {

    //item_table--------------------------------------
    @Query("SELECT * FROM item_table")
    fun getAllItems(): LiveData<List<ItemEntity>>

    @Query("SELECT * FROM item_table WHERE date_id IN (:dateId) ORDER BY id ASC")
    fun getDateItems(dateId: Int): LiveData<List<ItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: ItemEntity)


    //date_table--------------------------------------
    @Query("SELECT * FROM date_table")
    fun getAllDate(): LiveData<List<DateEntity>>

    @Query("SELECT * FROM date_table WHERE id IN (:dateId)")
    fun getDate(dateId: Int): LiveData<List<DateEntity>>

    @Query("SELECT * FROM date_table WHERE date IN (:d)")
    fun getDateId(d: String): LiveData<List<DateEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDate(date: DateEntity)


    //type_table--------------------------------------
    @Query("SELECT * FROM type_table ")
    fun getAllType(): LiveData<List<TypeEntity>>

    @Query("SELECT * FROM type_table WHERE id IN (:typeId)")
    fun getType(typeId: Int): LiveData<List<TypeEntity>>

    @Query("SELECT * FROM type_table WHERE type IN (:t)")
    fun getTypeId(t: String): LiveData<List<TypeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertType(type: TypeEntity)

    @Query("DELETE FROM type_table WHERE type IN (:t)")
    fun delType(t: String)
}

