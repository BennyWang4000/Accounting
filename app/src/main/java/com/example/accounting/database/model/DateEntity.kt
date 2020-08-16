package com.example.accounting.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "date_table")
data class DateEntity(
    @PrimaryKey val date: Int,
    @ColumnInfo(name = "sum") val sum: Int
)