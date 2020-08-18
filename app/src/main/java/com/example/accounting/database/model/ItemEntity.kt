package com.example.accounting.database.model

import androidx.room.*

//const val tableName: String = "20200101"

@Entity(
    tableName = "item_table",

    foreignKeys = [
        ForeignKey(
            entity = DateEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("date_id"),
            onDelete = ForeignKey.CASCADE),
        ForeignKey(
            entity = TypeEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("type_id"),
            onDelete = ForeignKey.CASCADE),
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("account_id"),
            onDelete = ForeignKey.CASCADE)],
    indices = [Index(value = ["date_id"])]
)
//CASCADE - 會將有所關聯的紀錄行也會進行刪除或修改。
//
//SET NULL - 會將有所關聯的紀錄行設定成 NULL。
//
//NO ACTION - 有存在的關聯紀錄行時，會禁止父資料表的刪除或修改動作。
//
//RESTRICT - 與 NO ACTION 相同。
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int= 1,
    @ColumnInfo(name= "date_id") val dateId: Int,
    @ColumnInfo(name= "type_id") val typeId: Int,
    @ColumnInfo(name= "name") val name: String,
    @ColumnInfo(name= "price") val price: Double,
    @ColumnInfo(name= "account_id") val accountId: Int,
    @ColumnInfo(name= "fixed_expense") val fixedExpense: Int
)