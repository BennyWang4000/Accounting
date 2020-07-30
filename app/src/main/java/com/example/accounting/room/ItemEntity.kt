package com.example.accounting.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

//const val tableName: String = "20200101"

@Entity(tableName = "item_table")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "item_id") val itemId: Int= 1,
    @ColumnInfo(name= "date") val date: String,
    @ColumnInfo(name= "id") val id: Int,
    @ColumnInfo(name= "type") val type: String,
    @ColumnInfo(name= "name") val name: String,
    @ColumnInfo(name= "note") val note: String,
    @ColumnInfo(name= "price") val price: Int
) {
}