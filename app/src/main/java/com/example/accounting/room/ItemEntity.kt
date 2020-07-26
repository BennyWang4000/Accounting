package com.example.accounting.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//const val tableName: String = "20200101"

@Entity(tableName = "item_table")
data class ItemEntity(
    @ColumnInfo(name = "date") val date: Int?,
    @ColumnInfo(name = "id") val itemId: Int?,
    @ColumnInfo(name= "title") val title: String?,
    @ColumnInfo(name= "body") val body: String?,
    @ColumnInfo(name= "type") val type: String?,
    @ColumnInfo(name= "price") val price: Int?
) {
}