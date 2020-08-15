package com.example.accounting.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type_table")
data class TypeEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name= "type_id") val typeId: Int,
    @ColumnInfo(name = "type")val type: String,
    @ColumnInfo(name = "image") val image: Int
)