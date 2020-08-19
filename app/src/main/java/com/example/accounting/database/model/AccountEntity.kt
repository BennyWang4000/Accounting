package com.example.accounting.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "account_table",
    indices = [Index(value = ["_id", "name"], unique = true)])
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") var id: Int,
    @ColumnInfo(name= "name") var account: String
)