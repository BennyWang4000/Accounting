package com.example.accounting.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//const val tableName: String = "20200101"

@Entity
data class ItemEntity(
//    @PrimaryKey @ColumnInfo(name = "date") val date: Int,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val itemId: Int,
    @ColumnInfo(name= "title") val title: String,
    @ColumnInfo(name= "body") val body: String,
    @ColumnInfo(name= "type") val type: String,
    @ColumnInfo(name= "price") val price: Int
) {
}