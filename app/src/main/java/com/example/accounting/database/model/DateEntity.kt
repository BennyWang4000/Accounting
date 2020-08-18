package com.example.accounting.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "date_table",
    indices = [Index(value = ["id", "date"], unique = true)]
)
data class DateEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int= 1,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "sum") val sum: Int
)