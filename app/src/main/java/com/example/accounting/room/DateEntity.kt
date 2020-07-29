package com.example.accounting.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "date_table")
data class DateEntity(
    @PrimaryKey val date: Int,
    @ColumnInfo(name = "items") val items: ItemEntity?
) {
}