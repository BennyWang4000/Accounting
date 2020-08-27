package com.example.accounting.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "category_table",
    indices = [Index(value = ["_id", "name"], unique = true)])
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name= "_id") var id: Int,
    @ColumnInfo(name = "name")var name: String,
    @ColumnInfo(name = "icon") var icon: String,
    @ColumnInfo(name= "category_order") var categoryOrder: Int,
    @ColumnInfo(name= "default_name") var defaultName: String,
    @ColumnInfo(name= "behavior") var behavior: Int
    //expense= 0; income= 1
)