package com.example.accounting.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "expense_table")
data  class ExpenseEntity (
    @ColumnInfo(name = "_id") val id: Int,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "descr") val descr: String,
    @ColumnInfo(name = "account_id") val account_id: Int,
    @ColumnInfo(name = "category_id") val category_id: Int,
    @ColumnInfo(name = "routine_id") val routine_id: Int
)