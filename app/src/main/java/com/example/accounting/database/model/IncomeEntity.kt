package com.example.accounting.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "income_table")
data class IncomeEntity (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") var id: Int,
    @ColumnInfo(name = "amount") var amount: Double,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "descr") var descr: String,
    @ColumnInfo(name = "account_id") var accountId: Int,
    @ColumnInfo(name = "category_id") var categoryId: Int,
    @ColumnInfo(name = "routine_id") var routineId: Int
)