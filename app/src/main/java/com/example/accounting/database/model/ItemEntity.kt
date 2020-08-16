package com.example.accounting.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

//const val tableName: String = "20200101"

@Entity(tableName = "item_table",
    indices = [Index(value = ["date"])]
)
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