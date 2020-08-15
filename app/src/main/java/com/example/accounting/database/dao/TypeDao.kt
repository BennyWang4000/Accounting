package com.example.accounting.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.accounting.database.model.TypeEntity

@Dao
interface TypeDao {
    //type
    @Query("SELECT * FROM type_table ")
    fun getAllType(): LiveData<List<TypeEntity>>

    @Query("SELECT * FROM type_table WHERE type IN (:t)")
    fun getType(t: String): LiveData<List<TypeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertType(type: TypeEntity)

    @Query("DELETE FROM type_table WHERE type IN (:t)")
    fun delType(t: String)
}