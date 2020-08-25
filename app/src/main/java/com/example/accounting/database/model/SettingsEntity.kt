package com.example.accounting.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "setting_table")
data class SettingsEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") var id: Int,
    @ColumnInfo(name = "default_account_index") var defaultAccountIndex: Int,
    @ColumnInfo(name = "budget_amount") var budgetAmount: Int,
    @ColumnInfo(name = "tracker_id") var TrackerID: String,
    @ColumnInfo(name = "budget_show") var budgetShow: Boolean,
    @ColumnInfo(name = "is_first_launch") var isFirstLaunch: Boolean,
    @ColumnInfo(name = "currency_code") var currencyCode: String,
    @ColumnInfo(name = "budget_start_date") var budgetStartDate: Int= 1,
    @ColumnInfo(name = "passcode") var passcode: String?
)