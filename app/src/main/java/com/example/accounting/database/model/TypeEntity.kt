package com.example.accounting.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "type_table",
    indices = [Index(value = ["id", "type"], unique = true)])
data class TypeEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name= "id") val id: Int,
    @ColumnInfo(name = "type")val type: String,
    @ColumnInfo(name = "icon") val icon: Int
)