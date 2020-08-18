package com.example.accounting.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "setting")
data class SettingsEntity(
    @ColumnInfo(name = "default_account_index") val defaultAccountIndex: Int= 0,
    @ColumnInfo(name = "budget_amount") val budgetAmount: Int,
    @ColumnInfo(name = "tracker_id") val TrackerID: String,
    @ColumnInfo(name = "budget_show") val budgetShow: Boolean,
    @ColumnInfo(name = "is_first_launch") val isFirstLaunch: Boolean,
    @ColumnInfo(name = "currency_code") val currencyCode: String= "TWD",
    @ColumnInfo(name = "budget_start_date") val budgetStartDate: Int= 1,
    @ColumnInfo(name = "passcode") val passcode: String= ""
)