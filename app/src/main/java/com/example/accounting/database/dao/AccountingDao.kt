package com.example.accounting.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.accounting.database.model.AccountEntity
import com.example.accounting.database.model.ExpenseEntity
import com.example.accounting.database.model.CategoryEntity
import com.example.accounting.database.model.RoutineEntity

@Dao
interface AccountingDao {

    //expense_table--------------------------------------
    @Query("SELECT * FROM expense_table")
    fun getAllExpenses(): LiveData<List<ExpenseEntity>>

    @Query("SELECT * FROM expense_table WHERE date IN (:d)")
    fun getDailyExpenses(d: String): LiveData<List<ExpenseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpense(expense: ExpenseEntity)

    //category_table--------------------------------------
    @Query("SELECT * FROM  category_table")
    fun getAllCategories(): LiveData<List<CategoryEntity>>

    @Query("SELECT * FROM category_table WHERE _id IN (:id)")
    fun getCategories(id: Int): LiveData<List<CategoryEntity>>

    @Query("SELECT * FROM category_table WHERE name IN (:n)")
    fun getTypeId(n: String): LiveData<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategory(category: CategoryEntity)

    @Query("DELETE FROM category_table WHERE _id IN (:id)")
    fun delCategory(id: Int)

    //account_table--------------------------------------
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAccount(account: AccountEntity)
//    @Query("INSERT INTO account_table VALUES (:i, :n)")
//    fun insertAccount(i: Int, n: String)

    //routine_table--------------------------------------
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRoutine(routine: RoutineEntity)
}

