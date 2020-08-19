package com.example.accounting.database.model

import androidx.room.*

//const val tableName: String = "20200101"

@Entity(
    tableName = "expense_table",
//    foreignKeys = [
//        ForeignKey(
//            entity = AccountEntity::class,
//            parentColumns = arrayOf("_id"),
//            childColumns = arrayOf("account_id"),
//            onDelete = ForeignKey.CASCADE),
//        ForeignKey(
//            entity = CategoryEntity::class,
//            parentColumns = arrayOf("_id"),
//            childColumns = arrayOf("category_id"),
//            onDelete = ForeignKey.CASCADE),
//        ForeignKey(
//            entity = RoutineEntity::class,
//            parentColumns = arrayOf("_id"),
//            childColumns = arrayOf("routine_id"),
//            onDelete = ForeignKey.CASCADE)],
    indices = [Index(value = ["_id"])]
)
//CASCADE - 會將有所關聯的紀錄行也會進行刪除或修改。
//
//SET NULL - 會將有所關聯的紀錄行設定成 NULL。
//
//NO ACTION - 有存在的關聯紀錄行時，會禁止父資料表的刪除或修改動作。
//
//RESTRICT - 與 NO ACTION 相同。
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") var id: Int,
    @ColumnInfo(name = "amount") var amount: Double,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "descr") var descr: String,
    @ColumnInfo(name = "account_id") var account_id: Int,
    @ColumnInfo(name = "category_id") var category_id: Int,
    @ColumnInfo(name = "routine_id") var routine_id: Int
)