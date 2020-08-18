package com.example.accounting.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "account_table",
    indices = [Index(value = ["id", "account"], unique = true)])
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int= 1,
    @ColumnInfo(name= "account") val account: String,
    @ColumnInfo(name= "is_default") val isDefault: Boolean
)