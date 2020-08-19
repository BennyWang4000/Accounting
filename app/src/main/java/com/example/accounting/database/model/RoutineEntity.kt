package com.example.accounting.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routine_table")
class RoutineEntity (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name= "_id") var _id: Int,
    @ColumnInfo(name= "amount") var amount: Double,
    @ColumnInfo(name= "behavior") var behavior: Int, //income=1 or expense= -1
    @ColumnInfo(name= "descr") var descr: String,
    @ColumnInfo(name= "cycle") var cycle: Int,
    @ColumnInfo(name= "date") var date: String,
    @ColumnInfo(name= "start_date") var start_date: String,
    @ColumnInfo(name= "account_id") var account_id: Int,
    @ColumnInfo(name= "category_id") var category_id: Int
)